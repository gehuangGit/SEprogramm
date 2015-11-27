package de.htwg.se.minesweeper.controller;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.minesweeper.model.Cell;
import de.htwg.se.minesweeper.model.Grid;
import de.htwg.se.minesweeper.modelInterface.CellInterface;

public class MScontrollerTest {

	MScontrollerInterface mc;
	Grid mf;
	List<Cell> list;

	@Before
	public void setUp() throws Exception {
		
		mc = new MScontroller();
		mc.setUpGrid(10, 10, 1);
		mf = new Grid(10, 10, 10);
		list = new LinkedList<Cell>();
	}
	@Test
	public void testinitialCheck() {
		assertTrue("Result", mc.initialCheck(10, 10, 3));
		assertFalse("Result", mc.initialCheck(10, 10, -1));
		assertFalse("Result", mc.initialCheck(-10, 10, 3));
		assertFalse("Result", mc.initialCheck(10, -10, 3));
		assertFalse("Result", mc.initialCheck(10, 10, 93));
	}
	@Test
	public void testGetSizeX() {
		assertEquals(10, mc.getSizeX());
	}

	@Test
	public void testGetY() {
		assertEquals(10, mc.getSizeY());
	}
	
	
	@Test
	public void testsetUpGrid() {
		MScontrollerInterface control = new MScontroller();
		control.setUpGrid("Easy");
		assertEquals(MScontrollerInterface.SIZE_X_EASY, control.getSizeX());
		assertEquals(MScontrollerInterface.SIZE_Y_EASY, control.getSizeY());
		assertEquals(MScontrollerInterface.MINES_EASY, control.getMines());
		
		control.setUpGrid("Normal");
		assertEquals(MScontrollerInterface.SIZE_X_NORMAL, control.getSizeX());
		assertEquals(MScontrollerInterface.SIZE_Y_NORMAL, control.getSizeY());
		assertEquals(MScontrollerInterface.MINES_NORMAL, control.getMines());
		
		control.setUpGrid("Hard");
		assertEquals(MScontrollerInterface.SIZE_X_HARD, control.getSizeX());
		assertEquals(MScontrollerInterface.SIZE_Y_HARD, control.getSizeY());
		assertEquals(MScontrollerInterface.MINES_HARD, control.getMines());
		
		control.setUpGrid("");

	}

	@Test
	public void testFirstClick() {
		mc = new MScontroller();
		mc.setUpGrid(10, 10, 0);
		assertEquals(-1, mc.firstClick(-1, 3));
		assertEquals(-1, mc.firstClick(2000, 3));
		assertEquals(-1, mc.firstClick(5, -4));
		assertEquals(-1, mc.firstClick(5, 30004));
		assertEquals(-1, mc.firstClick(-4, -4));
		assertEquals(-1, mc.firstClick(4000, 40));
		assertEquals(-1, mc.firstClick(-4, 4000));
		assertEquals(-1, mc.firstClick(4000, -4));
		assertEquals(2, mc.firstClick(3, 3));
		mc.setUpGrid(10, 10, 50);
		assertEquals(1, mc.firstClick(3, 3));

	}

	
	@Test
	public void testClick() {
		mc = new MScontroller();
		mc.setUpGrid(10, 10, 10);
		assertEquals(-1, mc.click(-1, 5, false, true));
		assertEquals(-1, mc.click(5, -1, true, true));
		assertEquals(-1, mc.click(5, 123, true, false));
		assertEquals(-1, mc.click(123, 5, false, false));
		mc.firstClick(5, 5);
		for (int x = 0; x < mc.getSizeX(); ++x) {
			for (int y = 0; y < mc.getSizeY(); ++y) {
				if (mc.getCellList()[x][y].isMine()) {
					assertEquals(1, mc.click(x + 1, y + 1, false, false));
				} else {
					int click = mc.click(x + 1, y + 1, false, false);
					if (mc.didIWin()) {
						assertEquals(2, click);
					} else {
						assertEquals(0, click);
					}

				}
			}
		}
		
	}
	
	@Test
	public void testSetFirstClickDone() {
		mc.resetFirstClickDone();
		assertFalse(mc.getFirstClickDoneStatus());
		mc.setFirstClickDone();
		assertTrue(mc.getFirstClickDoneStatus());
	}
	
	@Test
	public void testflagTesting() {
		CellInterface box = mc.getCellList()[3][2];

		box.setFlag();
		assertFalse(mc.flagTesting(box, false, false));
		assertTrue(mc.flagTesting(box, false, true));
		assertTrue(mc.flagTesting(box, true, false));
		assertTrue(mc.flagTesting(box, true, true));

		box.unsetFlag();
		assertFalse(mc.flagTesting(box, false, false));
		assertTrue(mc.flagTesting(box, false, true));
		assertTrue(mc.flagTesting(box, true, false));
		assertTrue(mc.flagTesting(box, true, true));
		
	}
	
	@Test
	public void testgetFlagStatus() {
		CellInterface box = mc.getCellList()[1][1];
		box.unsetFlag();
		assertFalse(mc.getFlagStatus(box));
		box.setFlag();
		assertTrue(mc.getFlagStatus(box));
	}

}
