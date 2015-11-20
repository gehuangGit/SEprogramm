package minesweeper;

import src.controller.MScontroller;
import src.viewGUI.Gui_Frame;
import src.viewTUI.TUI;

public class Minesweeper {
	public static void main(String[] args){
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
		TUI t = new TUI(m);
		t.playTUI();
		
		
		
	}

}
