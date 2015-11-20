package src.viewGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.controller.MScontroller;
import src.controller.MScontrollerInterface;
import src.model.Flag;
import src.observer.IMSObserverWithArguments;

@SuppressWarnings("serial")
public class Gui_Frame extends JFrame implements IMSObserverWithArguments {
	private static final int DEFAULT_Y = 600;
	private static final int DEFAULT_X = 600;

	private Container container;
	private Container gameField;
	private JButton buttonEasy;
	private JButton buttonNormal;
	private JButton buttonHard;
	private GUI_GridPanel ms;
	private GUI_GridPanel mm;
	private GUI_GridPanel mx;

	// private MScontroller mci;
	private MScontrollerInterface mci;

	private GUI_StatusPanel statusPanel;

	public Gui_Frame(MScontrollerInterface m) {

		container = this.getContentPane();
		gameField = new JPanel();

		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_X, DEFAULT_Y);
		this.setBackground(Color.LIGHT_GRAY);

		container.setLayout(null);
		this.mci = m;
		//mci = new MScontroller();

		/**
		 * statusPanel hier
		 */

		statusPanel = new GUI_StatusPanel();

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

	public static void main(String[] args) {
		MScontroller m = new MScontroller();
		new Gui_Frame(m);
	}

	class NewEasyGame implements ActionListener {
		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			gameField.removeAll();
			
			//mci = new MScontroller();
			//mci.restartE();
			ms = new GUI_GridPanel(mci, mci.SIZE_X_EASY, mci.SIZE_Y_EASY);
			ms.addIncrementObserver(Gui_Frame.this);

			gameField.add(ms);

			mci.setUpGrid("Easy");
			
			setVisible(true);
		}
	}

	class NewNormalGame implements ActionListener {

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			gameField.removeAll();
			
			mci = new MScontroller();
			
			mm = new GUI_GridPanel(mci, mci.SIZE_X_NORMAL, mci.SIZE_Y_NORMAL);
			mm.addIncrementObserver(Gui_Frame.this);
			gameField.add(mm);

			mci.setUpGrid("Normal");
			mm.repaint();
			
			setVisible(true);
		}
	}

	class NewHardGame implements ActionListener {

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			gameField.removeAll();
			
			mci = new MScontroller();
			
			mx = new GUI_GridPanel(mci, mci.SIZE_X_HARD, mci.SIZE_Y_HARD);
			mx.addIncrementObserver(Gui_Frame.this);
			gameField.add(mx);

			mci.setUpGrid("Hard");
			mx.repaint();
			
			setVisible(true);
		}
	}

	class NewGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	@Override
	public void update(Object arg) {
		Flag flag = (Flag) arg;
		statusPanel.setFlag(flag);
	}
}