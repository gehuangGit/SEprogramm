package de.htwg.se.minesweeper.viewTUI;

import static org.junit.Assert.*;

import org.junit.Test;

public class TUITest {
	TUI tui;
	
	@Test
	public void testtitle(){
	assertEquals("****************************************\n"
				+ "*                                         *\n"
				+ "*     Welcome to Minesweeper TUI game!    *\n"
				+ "*                                         *\n"
				+ "*****************************************", TUI.title());
	}
	
	
}
