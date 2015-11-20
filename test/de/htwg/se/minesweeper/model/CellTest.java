package de.htwg.se.minesweeper.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	Cell cell;
	
	@Before
	public void setup() {
		cell = new Cell(0,0);
	}

	@Test
	public void testIsClosed() {
		assertTrue(cell.isClosed());
	}

}
