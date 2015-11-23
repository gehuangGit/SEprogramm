package minesweeper;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.viewTUI.TUI;
import viewGUI.Gui_Frame;

public class Minesweeper {
	public static void main(String[] args){
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
		TUI t = new TUI(m);
		t.playTUI();
		
		
		
	}

}
