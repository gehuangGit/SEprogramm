package de.htwg.se.minesweeper.model;
import de.htwg.se.minesweeper.modelInterface.GridInterface;

/**
 * @author: gehuang
 * @datum: 23.10.2015
 */


public class Grid implements GridInterface {
	
	private int mines;
	private Cell[][] cellList;

	private int sizeX;
	private int sizeY;
	
	public Grid(int ix, int iy, int imines){
		this.sizeX = ix;
		this.sizeY = iy;
		this.mines = imines;
		
		cellList = new Cell[sizeX][sizeY];
		
		for(int x = 0; x < sizeX; ++x){
			for(int y = 0; y < sizeY; ++y){
				cellList[x][y] = new Cell(x, y);
			}
		}
	}
	
	@Override
	public int getMines(){
		return mines;
	}
	@Override
	public Cell[][] getCellList(){
		return cellList;
	}
	@Override
	public int getSizeX(){
		return this.sizeX;
	}
	@Override
	public int getSizeY(){
		return this.sizeY;
	}

	@Override
	public GridInterface getGrid() {
		return null;
	}
	
	
	
	
	
}
