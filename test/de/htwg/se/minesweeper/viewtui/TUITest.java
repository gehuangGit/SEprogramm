package de.htwg.se.minesweeper.viewtui;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.controller.MScontrollerInterface;
import de.htwg.se.minesweeper.viewtui.TUI;

public class TUITest {
	MScontrollerInterface ms = new MScontroller();
	TUI tui = new TUI(ms);
	
	@Test
	public void testtitle(){
		assertTrue(TUI.title().contains("Welcome to Minesweeper TUI game!"));
	}
	
	
}
