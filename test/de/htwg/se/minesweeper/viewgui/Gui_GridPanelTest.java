package de.htwg.se.minesweeper.viewgui;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.controller.MScontrollerInterface;
import de.htwg.se.minesweeper.viewgui.Gui_GridPanel;

public class Gui_GridPanelTest {
	MScontrollerInterface ms = new MScontroller();
	Gui_GridPanel gp = new Gui_GridPanel(ms, 5, 5);
	
	
	@Test
	public void testgetFieldBuild(){
		gp.buildUpMineGameField(5, 5);
		gp.setFieldBuild();
		assertTrue(gp.getFieldBuild());
	}
}
