package de.htwg.se.minesweeper.minesweeper;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.controller.MScontrollerInterface;
import de.htwg.se.minesweeper.viewGUI.Gui_Frame;
import de.htwg.se.minesweeper.viewTUI.TUI;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class Minesweeper {
	
	private static Minesweeper minesweeper;
	private Injector injector;
	private MScontrollerInterface controller;
	private Gui_Frame gui;
	private TUI tui;
	
	public Minesweeper(){
		this.injector = Guice.createInjector(new MinesweeperModule());
		this.controller = injector.getInstance(MScontrollerInterface.class);
		this.gui = injector.getInstance(Gui_Frame.class);
		this.tui = injector.getInstance(TUI.class);
		minesweeper = this;
	}
	
	public static Minesweeper getInstance(){
		return minesweeper == null ? new Minesweeper() : minesweeper;
	}
	
	public static void main(String[] args) throws InstantiationException {
		
		Minesweeper ms = Minesweeper.getInstance();
		MScontroller m = ms.getControl();
		
		new Gui_Frame(m);
		TUI t = new TUI(m);
		t.playTUI();
	}

	public MScontroller getControl(){
		return (MScontroller) controller;
	}
	
	public Gui_Frame getGui(){
		return gui;
	}
	
	public TUI getTui(){
		return tui;
	}
	
}
