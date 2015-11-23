package de.htwg.se.minesweeper.controller;

import de.htwg.se.minesweeper.modelInterface.CellInterface;
import de.htwg.se.minesweeper.modelInterface.GridInterface;
import de.htwg.se.minesweeper.observer.IMSObserver;

public interface MScontrollerInterface {

	/**
	 * Standardgroessen bei Schwierigkeit "EASY"
	 */
	int SIZE_X_EASY = 10;
	int SIZE_Y_EASY = 10;
	int MINES_EASY = 10;

	/**
	 * Standardgroessen bei Schwierigkeit "NORMAL"
	 */
	int SIZE_X_NORMAL = 15;
	int SIZE_Y_NORMAL = 15;
	int MINES_NORMAL = 35;

	/**
	 * Standardgroessen bei Schwierigkeit "HARD"
	 */
	int SIZE_X_HARD = 20;
	int SIZE_Y_HARD = 20;
	int MINES_HARD = 85;

	/**
	 * Maximale Anzahl der Minen pro Feld = 1/AMOUNT_OF_MINES.
	 */
	int AMOUNT_OF_MINES = 3;

	/**
	 * Zehn.
	 */
	int MAGIC_NUMBER_TEN = 10;

	int PLAYER_WON = 2;
	int PLAYER_LOST = 1;
	int NORMAL_BOX = 0;
	int WRONG_COORDS = -1;

	void addObserver(IMSObserver o);

	/**
	 * Methode checkt die Initialisierungswerte. Die Groesse darf nicht negativ
	 * sein, ausserdem darf die Anzahl an Minen nicht mehr als 1/AMONT_OF_MINES
	 * des Feldes belegen.
	 * 
	 * @param sizeX
	 *            X-Groesse
	 * @param sizeY
	 *            Y-Groesse
	 * @param mines
	 *            Anzahl Minen
	 * @return true, falls Werte OK, sonst false.
	 */
	boolean initialCheck(int sizeX, int sizeY, int mines);

	/**
	 * Die Methode setUpField() initialisiert die MineField-Variablen mit der
	 * Groesse in X-, der Groesse in Y-Richtung und der Anzahl an Minen auf dem
	 * Feld
	 * 
	 * @param x
	 *            Groesse in X-Richtung
	 * @param y
	 *            Groesse in Y-Richtung
	 * @param mines
	 *            Anzahl Minen
	 */
	void setUpGrid(int sizeX, int sizeY, int mines);

	/**
	 * Initialisiert das Feld mit Standardgroessen.
	 * 
	 * @param s
	 *            String "Anfaenger", "Fortgeschritten" oder "Profi"
	 */
	void setUpGrid(String s);

	/**
	 * FirstClick() behandelt den ersten Klick
	 * Dieser unterscheidet sich von
	 * den anderen Klicks dadurch, dass erst _nach_ dem ersten Klick die Minen
	 * auf dem Feld verteilt werden, denn der erste Klick darf _nie_ eine Mine
	 * sein. Somit verteilt diese Methode die Minen auf dem Feld. Erwartet
	 * User-Werte!
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @return NORMAL_BOX, if xCoord and yCoord was in Array Bounds,
	 *         WRONG_COORDS if not, PLAYER_WON if player won.
	 */
	int firstClick(int xCoord, int yCoord);

	/**
	 * Click() beandelt den allgemeinen ("nicht-ersten") Klick und ruft - Wenn
	 * das Feld keine Mine ist - die UncoverRec-Methode auf. Erwartet
	 * User-Eingaben!
	 * 
	 * @param xCoord
	 *            User-Eingabe des X-Wertes
	 * @param yCoord
	 *            User-Eingabe des Y-Wertes
	 * @return NORMAL_BOX if normal box, PLAYER_LOST if mine, WRONG_COORDS if
	 *         wrong coords, PLAYER_WON if player won
	 */
	int click(int xCoord, int yCoord, boolean flagRead, boolean unflagRead);

	/**
	 * Testet, ob die Click-Methode etwas mit Flaggen machen muss. Wenn sie eine
	 * Flagge gesetzt bzw. weggenommen hat, wird true zurückgegeben. Die
	 * Click-Methode kann dann also beendet werden. Ansonsten false.
	 * 
	 * @param box
	 *            Die Box, auf die evtl. eine Flagge gesetzt oder entfernt wird.
	 * @param flag
	 *            Ist flag true, wird eine Flagge gesetzt.
	 * @param unflag
	 *            Ist unflag true, wird die Flagge entfernt.
	 * @return true, wenn etwas gemacht wurde, sonst falst.
	 */
	boolean flagTesting(CellInterface box, boolean flag, boolean unflag);

	/**
	 * Gibt true zurueck, wenn der Player gewonnen hat. Gewonnen hat er genau
	 * dann, wenn alle nicht-Minen-Felder aufgedeckt sind.
	 * 
	 * @return
	 */
	boolean didIWin();

	/**
	 * @return Die Groesse des Grides in X-Richtung
	 */
	int getSizeX();

	/**
	 * @return Die Groesse des Grides in Y-Richtung
	 */
	int getSizeY();

	/**
	 * @return Methode gibt das CellInterface[][] getCellList zurueck.
	 */
	CellInterface[][] getCellList();

	/**
	 * MinesAround() gibt die Anzahl der Minen um ein gegebenes Feld zurueck.
	 * Sie benutzt die boxesAround()-Methode. Erwartet die Array-Werte!
	 * 
	 * @param x
	 *            Array-Wert in X-Richtung
	 * @param y
	 *            Array-Wert in Y-Richtung
	 * @return Anzahl der Minen, die das gegebene Feld umgeben.
	 */
	int minesAround(int x, int y);

	/**
	 * Fragt den Flag-Status einer MineBox ab.
	 * 
	 * @param box
	 *            Die Box, deren Flag-Status überprüft werden soll.
	 * @return True, falls Box eine Flag hat, sonst false.
	 */
	boolean getFlagStatus(CellInterface box);

	/**
	 * Gibt die Anzahl von Minen zurueck.
	 * 
	 * @return Anzahl an Minen.
	 */
	int getMines();

	/**
	 * boolean-Variable wird gesetzt, sobald erster Klcik getätigt wurde.
	 */
	void setFirstClickDone();

	/**
	 * Bei SPiel Nuestart muss die boolean-Variable geresettet werden.
	 */
	void resetFirstClickDone();
	
	/**
	 * 
	 * @return gibt Zustand der boolean-Variable zurück;
	 */
	boolean getFirstClickDoneStatus();
	
	boolean getGridSetUp();

	GridInterface getGrid();
}





