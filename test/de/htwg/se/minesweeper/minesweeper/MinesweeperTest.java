package de.htwg.se.minesweeper.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.minesweeper.controller.MScontroller;
import de.htwg.se.minesweeper.viewTUI.TUI;
import de.htwg.se.minesweeper.viewgui.Gui_Frame;

public class MinesweeperTest {
	Minesweeper m = new Minesweeper();
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetInstance() {
		Minesweeper mine = m.getInstance();
		assertNotNull(mine);
		assertSame(m, mine);
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
	
	@Test
	public void testGetControl(){
		MScontroller controll = m.getControl();
		assertNotNull(controll);
	}
}
