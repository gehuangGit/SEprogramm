package de.htwg.se.minesweeper.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	Cell mb;

	@Before
	public void setUp() throws Exception {
		mb = new Cell(12, 34);
	}

	@Test
	public void testMineBox() {
		assertEquals(12, mb.getPosX());
		assertEquals(34, mb.getPosY());
	}
	
	@Test
	public void testSetMine() {
		mb.setMine();
		assertTrue(mb.isMine());
	}
	
	@Test
	public void testgetPosX() {
		assertEquals(12, mb.getPosX());
	}
	
	@Test
	public void testgetPosY() {
		assertEquals(34, mb.getPosY());
	}
	
	@Test
	public void testisMine() {
		assertFalse(mb.isMine());
		mb.setMine();
		assertTrue(mb.isMine());
	}
	
	@Test
	public void testisCovered() {
		assertEquals(true, mb.isClosed());
	}
	
	@Test
	public void testuncover() {
		mb.open();
		assertEquals(false, mb.isClosed());
	}

	@Test
	public void testsetflag(){
		mb.setFlag();
		assertEquals(true, mb.getFlagStatus());
	}
	
	@Test
	public void testunsetflag(){
		mb.unsetFlag();
		assertEquals(false, mb.getFlagStatus());
	}

}
