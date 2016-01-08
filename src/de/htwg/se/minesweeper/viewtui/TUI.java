package de.htwg.se.minesweeper.viewtui;

/**
 * @author Ge Huang
 * @datum  13.11.2015
 */

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.controller.MScontrollerInterface;
import de.htwg.se.minesweeper.modelinterface.CellInterface;
import de.htwg.se.minesweeper.observer.IMSObserver;

public class TUI implements IMSObserver {

	private MScontrollerInterface mc;
	
	private Logger logger = LogManager.getLogger(TUI.class.getName());
	private String newLine = System.getProperty("line.separator");
	private String eingabe = "Eingabe: ";
	
	// constructor
	@Inject
	public TUI(MScontrollerInterface mc) {
		this.mc = mc;
		mc.addObserver(this);
	}

	// main method
	public static void main(String[] args) {
		MScontroller mcr = new MScontroller();
		TUI t = new TUI(mcr);
		t.playTUI();
	}

	/**
	 * playTUI is the most important methode, which 
	 * would be played in the TUI of minesweeper.
	 */
	public void playTUI() {
		// define the initial elements
		logger.info(newLine + title());
		
		logger.info(newLine + "sizeX: ");
		int sizeX = readPositiveInt();
		logger.info(newLine + eingabe + sizeX);
		
		logger.info(newLine + "sizeY: ");
		int sizeY = readPositiveInt();
		logger.info(newLine + eingabe + sizeY);
		
		// remove (int)
		logger.info(newLine + "mines (max. " + sizeX * sizeY
					/ MScontrollerInterface.AMOUNT_OF_MINES + "): "); 
		
		int mines = readNumberOfMines();

		// first click
		if (mc.initialCheck(sizeX, sizeY, mines)) {
			mc.setUpGrid(sizeX, sizeY, mines);
			
			logger.info(newLine + printTUI());
			
			logger.info(newLine + "X-Koordinate: ");
			int xCoord = readPositiveInt();
			logger.info(newLine + eingabe + xCoord);

			logger.info(newLine + "Y-Koordinate: ");
			int yCoord = readPositiveInt();
			logger.info(newLine + eingabe + yCoord);
			
			int firstClick = mc.firstClick(yCoord, xCoord);
			if (firstClick == -1) {
				logger.info(newLine + "Out of Bounds!");
				return;
			}
			if (firstClick == 2) {
				logger.info(newLine + "Gewonnen!");
				return;
			}
		} else {
			logger.info(newLine + "Falsche Werte!");
		}

		/**
		// play the TUI in a while loop.
		while (true) {
			System.out.println("\n\n");
			System.out.println("give a value:");
			System.out.println("X-Koordinate: ");
			int xCoord = readPositiveInt();
			System.out.println(eingabe + xCoord);

			System.out.println("Y-Koordinate: ");
			int yCoord = readPositiveInt();
			System.out.println(eingabe + yCoord);

			String tmp = null;
			flag = false;
			unflag = false;
			System.out.println("Are you want to set a flag? y/n");
			tmp = this.read();

			if (tmp.equals("y")) {
				flag = true;
			} else {
				flag = false;
			}
			System.out.println("Value: "+ yCoord + " " + xCoord);
			int result = mc.click(yCoord, xCoord, flag, unflag);
			if (result == mc.PLAYER_LOST) {
				System.out.println("You lose!");
				break;
			} else if (result == mc.PLAYER_WON) {
				System.out.println("You win!");
				break;
			}
		}
*/
	}

	public static String title() {
		
		return ( "*                                         *\n"
				+ "*     Welcome to Minesweeper TUI game!    *\n"
				+ "*                                         *\n");
	}

	/**
	 * read a positive Interger from the console.
	 * @return  positive Integer, if false, then return -1
	 */
	@SuppressWarnings("resource")
	private int readPositiveInt() {
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			int number = sc.nextInt();
			if (number > 0) {
				return number;
			}
		}
		return -1;
	}

	/**
	 * Liest eine Zahl von der Konsole, die die Anzahl an Minen beschreibt.
	 * 
	 * @return Korrekte Zahl der Minen-Anzahl, bei Fehler -1.
	 */
	private int readNumberOfMines() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			int number = sc.nextInt();
			if (number >= 0) {
				return number;
			}
		}
		return -1;
	}

	/**
	@SuppressWarnings("unused")
	private String read() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		logger.info(number);
		return number;
	}
*/
	/**
	 * printTUI() paint the TUI status
	 * @return a list of strings.
	 */
	private String printTUI() {
		StringBuilder sb = new StringBuilder();

		/* X-Achsen-Beschriftung */
		sb.append("     ");
		for (int y = 0; y < mc.getSizeX(); ++y) {
			if ((y + 1) < MScontrollerInterface.MAGIC_NUMBER_TEN) {
				sb.append("{ " + (y + 1) + "}");
			} else {
				sb.append("{" + (y + 1) + "}");
			}
		}
		sb.append("\n");

		/* Feld + Y-Achsen-Beschriftung an StringBuilder anhängen */
		buildTUI(sb);

		return sb.toString();
	}

	/**
	 * build the field with Y-Axis together.
	 * @param StringBuilder sb
	 *        the StringBuilder, which contains the X-Axis
	 */
	public void buildTUI(StringBuilder sb) {
		for (int x = 0; x < mc.getSizeY(); ++x) {
			if ((x + 1) < MScontrollerInterface.MAGIC_NUMBER_TEN) {
				sb.append("{ " + (x + 1) + "} ");
			} else {
				sb.append("{" + (x + 1) + "} ");
			}
			CellInterface box;
			for (int y = 0; y < mc.getSizeX(); ++y) {
				box = mc.getCellList()[y][x];
				if (mc.getFlagStatus(box)) {
					sb.append("[ F]");
				} else if (!mc.getFlagStatus(box) && box.isClosed()) {
					sb.append("[  ]");
				} else if (box.isClosed()) {
					sb.append("[  ]");
				} else if (box.isMine()) {
					sb.append("[ X]");
				} else {
					sb.append("[ " + mc.minesAround(y, x) + "]");
				}
			}
			sb.append("\n");
		}
	}

	@Override
	public void update() {
		logger.info(printTUI());

	}

}
