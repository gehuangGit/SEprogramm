package src.modelInterface;


/**
 * @author: gehuang
 * @datum: 23.10.2015
 */

public interface GridInterface {
	/**
	 * @return Das 2-dimensionale Array, das das Feld enthaelt.
	 */
	CellInterface[][] getCellList();

	/**
	 * @return Groesse in X-Richtung.
	 */
	int getSizeX();

	/**
	 * @return Groesse in Y-Richtung.
	 */
	int getSizeY();

	/**
	 * @return Anzahl der Minen auf dem Feld.
	 */
	int getMines();
}
