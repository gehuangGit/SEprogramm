package de.htwg.se.minesweeper.controller;
/** * @author  Ge Huang E-mail gehuang@htwg-konstanz.de: 
 * @date create timeï¼š2015å¹´10æœˆ18æ—¥ ä¸‹å�ˆ3:39:14 
 */



import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.modelInterface.CellInterface;
import de.htwg.se.minesweeper.modelInterface.GridInterface;
import de.htwg.se.minesweeper.observer.MSObservable;


//@Singleton
public class MScontroller extends MSObservable implements MScontrollerInterface {
	// Das MineField.
	private GridInterface grid;
	
	// Boolean zum ueberpruefen, ob erster Klcik schon getaetigt wurde.
	private boolean firstClickDone = false;
	private boolean gridSetUp = false;
	
	//sets the fieldSetUp-variable
	public void setGridSetUp(boolean gridSetUp) {
		this.gridSetUp = gridSetUp;
	}
	
	@Override
	public boolean initialCheck(int sizeX, int sizeY, int mines) {
		return sizeX != -1 && sizeY != -1 && mines != -1
				&& mines <= (int) (sizeX * sizeY / AMOUNT_OF_MINES);
	}

	@Override
	public void setUpGrid(int sizeX, int sizeY, int mines) {
		grid = new Grid(sizeX, sizeY, mines);
		gridSetUp = true;
		firstClickDone = false;
		notifyObservers();
	}

	@Override
	public void setUpGrid(String s) {
		
		//if (s.equals("Easy"))
		if ("Easy".equals(s)) {
			grid = new Grid(SIZE_X_EASY, SIZE_Y_EASY,
					MINES_EASY);
		} else if ("Normal".equals(s)) {
			grid = new Grid(SIZE_X_NORMAL,
					SIZE_Y_NORMAL, MINES_NORMAL);
		} else if ("Hard".equals(s)) {
			grid = new Grid(SIZE_X_HARD, SIZE_Y_HARD, MINES_HARD);
		}
		
		gridSetUp = true;
		firstClickDone = false;
		notifyObservers();
		
	}

	@Override
	public int firstClick(int xCoord, int yCoord) {
		LinkedList<Double> coords = new LinkedList<Double>();

		if (xCoord > 0 && xCoord <= grid.getSizeX() && yCoord > 0
				&& yCoord <= grid.getSizeY()) {
			coords.add(((xCoord - 1) + ((double) (yCoord - 1) / MAGIC_NUMBER_TEN)));

			Random rand = new Random();
			int mineX;
			int mineY;
			for (int i = 0; i < grid.getMines(); ++i) {
				do {
					mineX = rand.nextInt(grid.getSizeX());
					mineY = rand.nextInt(grid.getSizeY());
				} while (coords.contains(mineX
						+ ((double) mineY / MAGIC_NUMBER_TEN)));
				coords.add((mineX + ((double) mineY / MAGIC_NUMBER_TEN)));
				grid.getCellList()[mineX][mineY].setMine();
			}
			uncoverRec(xCoord - 1, yCoord - 1);
			notifyObservers();
			if (didIWin()) {
				uncoverAllMines();
				notifyObservers();
				return PLAYER_WON;
			}
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * UncoverRec() deckt das gegebene Feld auf, und - sollte es eine "0" sein -
	 * rekursiv alle weitern "Nullen" und die angrenzenden "Zahlen" im Umkreis.
	 * Erwartet Array-Werte!
	 * 
	 * @param x
	 *            Array-Wert in X-Richtung
	 * @param y
	 *            Array-Wert in Y-Richtung
	 */
	private void uncoverRec(int x, int y) {
		CellInterface currentBox = getCellList()[x][y];
		List<CellInterface> list = boxesAround(x, y);

		currentBox.open();

		if (minesAround(x, y) > 0) {
			return;
		}

		for (CellInterface cell : list) {
			if (cell.isClosed()) {
				uncoverRec(cell.getPosX(), cell.getPosY());
			}
		}

	}
	
	/**
	 * BoxesAround() gibt eine Liste zurueck, die alle Felder um ein gegebenes
	 * Feld enthaelt. (Das sind meistens 8 Stueck, allerdings kann das gegebene
	 * Feld auch an einer Seite oder in einer Ecke sein). Erwartet die
	 * Array-Werte!
	 * 
	 * @param xCoord
	 *            Array-Wert in X-Richtung.
	 * @param yCoord
	 *            Array-Wert in Y-Richtung.
	 * @return Liste mit allen umgebenen Boxen.
	 */
	public List<CellInterface> boxesAround(int x, int y) {
		List<CellInterface> list = new LinkedList<CellInterface>();

		addBox(x - 1, y - 1, list);
		addBox(x - 1, y, list);
		addBox(x - 1, y + 1, list);
		addBox(x, y - 1, list);
		addBox(x, y + 1, list);
		addBox(x + 1, y - 1, list);
		addBox(x + 1, y, list);
		addBox(x + 1, y + 1, list);

		return list;
	}
	
	/**
	 * Fuegt eine Box - falls sie existiert - an die boxesAround-Liste an.
	 * 
	 * @param x
	 *            X-Koordinate der Box.
	 * @param y
	 *            Y-Koordinate der Box.
	 * @param list
	 *            Die Liste, an die angefï¿½gt wird.
	 */
	private void addBox(int x, int y, List<CellInterface> list) {
		int sizeX = grid.getSizeX() - 1;
		int sizeY = grid.getSizeY() - 1;
		CellInterface[][] tmpGrid = grid.getCellList();
		if (x >= 0 && x <= sizeX && y >= 0 && y <= sizeY) {
			list.add(tmpGrid[x][y]);
		}
	}
	
	@Override
	public int click(int xCoord, int yCoord, boolean flag,
			boolean unflag) {
		if (xCoord <= 0 || xCoord > grid.getSizeX() || yCoord <= 0
				|| yCoord > grid.getSizeY()) {
			return -1;
		}

		CellInterface box = grid.getCellList()[xCoord - 1][yCoord - 1];
		if (flagTesting(box, flag, unflag)) {
			return 0;
		}

		if (box.isMine()) {
			uncoverAllMines();
			notifyObservers();
			return PLAYER_LOST;
		}
		if (box.isClosed() && box.isFlag()) {
			return 0;
		}

		uncoverRec(xCoord - 1, yCoord - 1);
		notifyObservers();
		if (didIWin()) {
			uncoverAllMines();
			notifyObservers();
			return PLAYER_WON;
		}
		return NORMAL_BOX;

	}

	@Override
	public boolean flagTesting(CellInterface cell, boolean flag, boolean unflag) {
		if (cell.isFlag() && unflag) {
			cell.unsetFlag();
			notifyObservers();
			return true;
		}

		if (!cell.isFlag() && unflag) {
			notifyObservers();
			return true;
		}
		if (flag && cell.isClosed()) {
			cell.setFlag();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public boolean didIWin() {
		CellInterface[][] cell = grid.getCellList();
		boolean test = true;
		for (int x = 0; x < getSizeX(); ++x) {
			for (int y = 0; y < getSizeY(); ++y) {
				if (!cell[x][y].isMine() && cell[x][y].isClosed()) {
					test = false;
				}
			}
		}
		return test;
	}

	@Override
	public int getSizeX() {
		return grid.getSizeX();
	}

	@Override
	public int getSizeY() {
		return grid.getSizeY();
	}

	@Override
	public CellInterface[][] getCellList() {
		return grid.getCellList();
	}

	/**
	 * gebe die Anzahl der Mines zurueck.
	 */
	@Override
	public int minesAround(int x, int y) {
		int minesAround = 0;
		for (CellInterface cell : this.boxesAround(x, y)) {
			if (cell.isMine()) {
				minesAround++;
			}
		}
		return minesAround;
	}

	@Override
	public boolean getFlagStatus(CellInterface box) {
		return grid.getCellList()[box.getPosX()][box.getPosY()].isFlag();
	}

	@Override
	public int getMines() {
		return grid.getMines();
	}
	
	/**
	 * Deckt alle Minen auf (am Ende vom Spiel)
	 */
	private void uncoverAllMines() {
		CellInterface[][] cellList = grid.getCellList();
		for (int x = 0; x < grid.getSizeX(); ++x) {
			for (int y = 0; y < grid.getSizeY(); ++y) {
				if (cellList[x][y].isMine()) {
					cellList[x][y].open();
				}
			}
		}
	}
	@Override
	public void setFirstClickDone() {
		this.firstClickDone = true;
	}

	@Override
	public void resetFirstClickDone() {
		this.firstClickDone = false;
	}

	@Override
	public boolean getFirstClickDoneStatus() {
		return this.firstClickDone;
	}

	@Override
	public boolean getGridSetUp() {
		return this.gridSetUp;
	}

	@Override
	public GridInterface getGrid() {
		return grid;
	}
	
}
