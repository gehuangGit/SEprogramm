package de.htwg.se.minesweeper.viewtui;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.viewtui.TUI;

public class TUITest {
	TUI tui;
	
	@Test
	public void testtitle(){
		assertTrue(TUI.title().contains("Welcome to Minesweeper TUI game!"));
	}
	
	
}
