package de.htwg.se.minesweeper.model;

import de.htwg.se.minesweeper.modelinterface.CellInterface;


/**
 * @author: gehuang
 * @datum: 23.10.2015
 */

public class Cell implements CellInterface{

	//Ist diese Box eine Mine?
	private boolean mine = false;
	
	//Ist diese Box zu- oder aufgedeckt?
	private boolean closed = true;
	
	//Ist auf der Box eine Flagge?
	private boolean flag = false;

	// X-Position der Box.
	private int posX;

	// Y-Position der Box.
	private int posY;
	
	/**
	 * Konstruktor weist der Box seine X- und Y-Koordinate zu (Array-Werte!).
	 * @param posX X-Koordinate
	 * @param posY Y-Koordinate
	 */
	public Cell(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	public void open() {
		this.closed = false;
	}

	@Override
	public boolean isClosed() {
		return this.closed;
	}

	@Override
	public int getPosX() {
		return this.posX;
	}

	@Override
	public int getPosY() {
		return this.posY;
	}

	@Override
	public boolean isFlag() {
		return this.flag;
	}

	@Override
	public void unsetFlag() {
		this.flag = false;
	}

	@Override
	public void setFlag() {
		this.flag = true;
	}

	@Override
	public boolean isMine() {
		return this.mine;
	}

	@Override
	public void setMine() {
		this.mine = true;
	}
	
	@Override
	public boolean getFlagStatus(){
		return flag;
	}
}
