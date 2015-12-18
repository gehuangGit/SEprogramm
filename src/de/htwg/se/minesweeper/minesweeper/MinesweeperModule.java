package de.htwg.se.minesweeper.minesweeper;

import com.google.inject.AbstractModule;

import de.htwg.se.minesweeper.controller.MScontrollerInterface;

public class MinesweeperModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MScontrollerInterface.class).to(de.htwg.se.minesweeper.controller.MScontroller.class);
		
	}

}
