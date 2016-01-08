package de.htwg.se.minesweeper.viewgui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.inject.Inject;

import de.htwg.se.minesweeper.controller.MScontrollerInterface;
import de.htwg.se.minesweeper.model.Flag;
import de.htwg.se.minesweeper.observer.IMSObserverWithArguments;

@SuppressWarnings("serial")
public class Gui_Frame extends JFrame implements IMSObserverWithArguments {
	private static final int DEFAULT_Y = 600;
	private static final int DEFAULT_X = 600;

	private Container container;
	private Container gameField;
	private JButton buttonEasy;
	private JButton buttonNormal;
	private JButton buttonHard;
	private Gui_GridPanel ms;
	private Gui_GridPanel mm;
	private Gui_GridPanel mx;

	private MScontrollerInterface mci;

	private Gui_StatusPanel statusPanel;

	@Inject
	public Gui_Frame(MScontrollerInterface m) {

		container = this.getContentPane();
		gameField = new JPanel();

		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_X, DEFAULT_Y);
		this.setBackground(Color.LIGHT_GRAY);

		container.setLayout(null);
		this.mci = m;
		/**
		 * statusPanel hier
		 */

		statusPanel = new Gui_StatusPanel();

		container.add(statusPanel);
		container.add(statusPanel);
		
		/**
		 * msPanel hier
		 */
		JPanel buttonPanel = new JPanel();

		buttonEasy = new JButton("Easy");
		buttonEasy.addActionListener(new NewEasyGame());
		buttonPanel.add(buttonEasy);

		buttonNormal = new JButton("Normal");
		buttonNormal.addActionListener(new NewNormalGame());
		buttonPanel.add(buttonNormal);

		buttonHard = new JButton("Hard");
		buttonHard.addActionListener(new NewHardGame());
		buttonPanel.add(buttonHard);

		buttonPanel.setBackground(Color.yellow);
		buttonPanel.setBounds(0, 40, 600, 40);
		buttonPanel.setVisible(true);
		container.add(buttonPanel);

		gameField.setLayout(null);
		gameField.setBounds(0, 0, 600, 600);		
		container.add(gameField);

		this.setVisible(true);
	}

	class NewEasyGame implements ActionListener {
		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gameField.removeAll();
			mci.setUpGrid("Easy");
			ms = new Gui_GridPanel(mci, mci.SIZE_X_EASY, mci.SIZE_Y_EASY);
			ms.addIncrementObserver(Gui_Frame.this);
			gameField.add(ms);
			setVisible(true);
		}
	}

	class NewNormalGame implements ActionListener {

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			gameField.removeAll();
			mci.setUpGrid("Normal");
			mm = new Gui_GridPanel(mci, mci.SIZE_X_NORMAL, mci.SIZE_Y_NORMAL);
			mm.addIncrementObserver(Gui_Frame.this);
			gameField.add(mm);
			mm.repaint();
			setVisible(true);
		}
	}

	class NewHardGame implements ActionListener {

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			gameField.removeAll();
			mci.setUpGrid("Hard");
			mx = new Gui_GridPanel(mci, mci.SIZE_X_HARD, mci.SIZE_Y_HARD);
			mx.addIncrementObserver(Gui_Frame.this);
			gameField.add(mx);
			mx.repaint();
			setVisible(true);
		}
	}

	class NewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// just implements the method of ActionListener
		}
	}

	@Override
	public void update(Object arg) {
		Flag flag = (Flag) arg;
		statusPanel.setFlag(flag);
	}
}