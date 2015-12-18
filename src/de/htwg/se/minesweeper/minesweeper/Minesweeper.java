package de.htwg.se.minesweeper.minesweeper;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.viewGUI.Gui_Frame;
import de.htwg.se.minesweeper.viewTUI.TUI;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class Minesweeper {
	
	private Injector injectior;
	
	
	
	public static void main(String[] args) throws InstantiationException {
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
		TUI t = new TUI(m);
		t.playTUI();
	}

}
