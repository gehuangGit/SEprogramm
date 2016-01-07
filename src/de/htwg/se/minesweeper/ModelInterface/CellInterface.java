package de.htwg.se.minesweeper.ModelInterface;


/**
 * @author: gehuang
 * @datum: 23.10.2015
 */

public interface CellInterface {

	/**
	 * open a uncovered cell.
	 */
	// uncover()
	void open();

	/**
	 * whether the cell closed or not, if yes, then give this method a "true".
	 */
	// isCovered()
	boolean isClosed();

	/**
	 * give the X-Position of the cell.
	 */
	int getPosX();

	/**
	 * give the Y-Position of the cell.
	 */
	int getPosY();

	/**
	 * whether the flag is on the cell.
	 */
	boolean isFlag();

	/**
	 * remove the flag on the cell.
	 */
	void unsetFlag();
	
	boolean getFlagStatus();

	/**
	 * set the flag on the cell.
	 */
	void setFlag();

	/**
	 * whether the mine is on the cell.
	 */
	boolean isMine();

	/**
	 * set a mine on the cell.
	 */
	void setMine();
	
}
