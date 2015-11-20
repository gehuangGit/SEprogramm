package minesweeper;

import controller.MScontroller;
import viewGUI.Gui_Frame;
import viewTUI.TUI;

public class Minesweeper {
	public static void main(String[] args){
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
		TUI t = new TUI(m);
		t.playTUI();
		
		
		
	}

}
