package de.htwg.se.minesweeper.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.viewGUI.Gui_Frame;
import de.htwg.se.minesweeper.viewTUI.TUI;

public class MinesweeperTest {
	Minesweeper m = new Minesweeper();
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetInstance() {
		Minesweeper mine = m.getInstance();
		assertNotNull(mine);
	}

	@Test
	public void testGetGui() {
		Gui_Frame gui = m.getGui();
		assertNotNull(gui);
	}
	
	@Test
	public void testGetTui() {
		TUI tui = m.getTui();
		assertNotNull(tui);
	}
}
