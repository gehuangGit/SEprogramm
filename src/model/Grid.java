package model;
import modelInterface.GridInterface;

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
	
	public int getMines(){
		return mines;
	}
	
	public Cell[][] getCellList(){
		return cellList;
	}
	
	public int getSizeX(){
		return this.sizeX;
	}
	
	public int getSizeY(){
		return this.sizeY;
	}
	
	
	
	
	
}
