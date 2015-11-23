package viewGUI;

import de.htwg.se.minesweeper.controller.MScontroller;

public class GUI {
	public static void main(String[] args){
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
	}
	

}
