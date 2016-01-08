package de.htwg.se.minesweeper.viewgui;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.viewgui.Gui_StatusPanel;

public class Gui_StatusPanelTest {
	Gui_StatusPanel sp = new Gui_StatusPanel();
	
	@Test
	public void testGui_StatusPanel(){
		assertNotNull(sp);
	}

}
