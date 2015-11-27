package de.htwg.se.minesweeper.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
	Grid mf;

	@Before
	public void setUp() throws Exception {
		mf = new Grid(5, 5, 3);
	}

	@Test
	public void testgetMines() {
		assertEquals(3, mf.getMines());
	}
	
	@Test
	public void testgetSizeX(){
		assertEquals(5, mf.getSizeX());		
	}
	
	@Test
	public void testgetSizeY(){
		assertEquals(5, mf.getSizeY());
	}
}
