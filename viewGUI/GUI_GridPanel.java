package src.viewGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.controller.MScontrollerInterface;
import src.model.Flag;
import src.observer.IMSObserverWithArguments;
import src.observer.MSObservableWithArguments;

@SuppressWarnings("serial")
public class GUI_GridPanel extends JPanel {

	private boolean fieldbuild = false;
	private MineButton[][] field;
	private MScontrollerInterface control;
	private MSObservableWithArguments incrementObservable;

	public GUI_GridPanel(MScontrollerInterface c, int x, int y) {
		control = c;
		this.setLayout(null);
		this.setBounds(0, 80, 600, 520);
		setLayout(new GridLayout(y, x, 0, 0));
		buildUpMineGameField(x, y);
		incrementObservable = new MSObservableWithArguments();
	}

	public void addIncrementObserver(IMSObserverWithArguments s) {
		incrementObservable.addObserver(s);
	}

	private void buildUpMineGameField(int sizeX, int sizeY) {
		field = new MineButton[sizeX][sizeY];
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				field[j][i] = new MineButton(control, j, i);
				add(field[j][i]);
			}
		}
	}

	public MineButton[][] getMineButtonFiled() {
		return field;
	}

	public void unsetFieldBuild() {
		fieldbuild = false;
	}

	public void setFieldBuild() {
		fieldbuild = true;
	}

	public boolean getFieldBuild() {
		return fieldbuild;
	}

	protected class MineButton extends JButton {
		private static final int DEFAULTSIZE = 40;
		private Dimension d = new Dimension(DEFAULTSIZE, DEFAULTSIZE);
		private MScontrollerInterface c;

		private int posX;
		private int posY;

		private int zeile;
		private int spalte;

		private static final int FONTSIZE = 10;
		private Font font = new Font("Dialog", Font.BOLD, FONTSIZE);
		private Icon flag = new ImageIcon("../se/src/src/viewGUI/flag.png");
		private Icon mine = new ImageIcon("../se/src/src/viewGUI/mine.jpg");

		protected MineButton(MScontrollerInterface mci, int x, int y) {
			c = mci;
			posX = x;
			posY = y;
			setPreferredSize(d);
			setFont(font);
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Object o = e.getButton();
					if (o.equals(MouseEvent.BUTTON1)) {
						spalte = posX;
						zeile = posY;
						// System.out.println("left" + zeile + " : " + spalte);

						mouseButton1Clicked();
					} else if (o.equals(MouseEvent.BUTTON3)) {
						spalte = posX;
						zeile = posY;
						// System.out.println("right" + zeile + " : " + spalte);
						mouseButton3Clicked();
					}
				}

			});
		}

		@SuppressWarnings("static-access")
		private void mouseButton1Clicked() {

			if (!c.getCellList()[spalte][zeile].getFlagStatus()) {
				if (!c.getFirstClickDoneStatus()) {
					c.firstClick(posX + 1, posY + 1);

					String text = "" + c.minesAround(posX, posY);
					if (c.minesAround(posX, posY) == 0) {
						recusivOpen(posX, posY);
					}
					Font font = new Font("Arial", 1, 20);
					MineButton.this.setForeground(Color.GREEN);
					MineButton.this.setFont(font);
					MineButton.this.setText(text);

					c.setFirstClickDone();
				} else {
					int ret = c.click(posX + 1, posY + 1, false, false);
					String text = "" + c.minesAround(posX, posY);
					Font font = new Font("Arial", 1, 20);
					MineButton.this.setForeground(Color.BLUE);
					MineButton.this.setFont(font);
					MineButton.this.setText(text);

					if (c.minesAround(spalte, zeile) == 0) {
						recusivOpen(spalte, zeile);
					}

					// if game lost
					if (ret == c.PLAYER_LOST) {
						GUI_GridPanel.this.unsetFieldBuild();
						for (int i = 0; i < control.getSizeY(); i++) {
							for (int j = 0; j < control.getSizeX(); j++) {
								field[j][i].setEnabled(false);
								if (control.getCellList()[j][i].isMine()) {
									field[j][i].setIcon(mine);
								}
							}
						}
						JOptionPane.showMessageDialog(null,
								"You lose the game",
								"Infomation", JOptionPane.WARNING_MESSAGE);

						System.exit(0);
					}
					// if game win
					if (ret == c.PLAYER_WON) {
						String eingabe = JOptionPane.showInputDialog(null,
								"You win the game, your name please:",
								"Congratulation",
								JOptionPane.PLAIN_MESSAGE);

						System.exit(0);
					}

				}
			}

		}

		private void mouseButton3Clicked() {

			if (!c.getCellList()[spalte][zeile].getFlagStatus()) {
				MineButton.this.setIcon(flag);
				MineButton.this.repaint();
				// c.getCellList()[spalte][zeile].setFlag();
				c.flagTesting(c.getCellList()[spalte][zeile], true, false);
				incrementObservable.notifyObservers(Flag.INCREMENT);
			} else {
				unflag();
				incrementObservable.notifyObservers(Flag.DECREMENT);
			}

		}

		private void unflag() {
			MineButton.this.setIcon(null);
			MineButton.this.repaint();
			c.getCellList()[posX][posY].unsetFlag();
		}

		private void recusivOpen(int s, int z) {
			String text = "";
			Font font = new Font("Arial", 1, 20);
			MineButton.this.setForeground(Color.BLUE);
			if (s - 1 > -1 && z - 1 > -1 && z - 1 < c.getSizeY()
					&& s - 1 < c.getSizeY()) {
				MineButton b1 = field[s - 1][z - 1];
				text = "" + c.minesAround(s - 1, z - 1);
				if (c.minesAround(s - 1, z - 1) == 0) {
					recusivOpen(s - 1, z - 1);
				}
				b1.setText(text);
				b1.setFont(font);
			}
			if (s - 1 > -1 && z + 1 > -1 && z + 1 < c.getSizeY()
					&& s - 1 < c.getSizeY()) {
				MineButton b2 = field[s - 1][z + 1];
				text = "" + c.minesAround(s - 1, z + 1);
				if (c.minesAround(s - 1, z + 1) == 0) {
					recusivOpen(s - 1, z + 1);
				}
				// Ok
				b2.setText(text);
				b2.setFont(font);
			}

			if (s + 1 > -1 && z + 1 > -1 && s + 1 < c.getSizeX()
					&& z + 1 < c.getSizeY()) {
				MineButton b3 = field[s + 1][z + 1];
				text = "" + c.minesAround(s + 1, z + 1);
				if (c.minesAround(s + 1, z + 1) == 0) {
					// recusivOpen(s+1, z+1);
				}
				b3.setText(text);
				b3.setFont(font);

			}
			if (s > -1 && z + 1 > -1 && z + 1 < c.getSizeY()
					&& s < c.getSizeY()) {
				MineButton b4 = field[s][z + 1];
				text = "" + c.minesAround(s, z + 1);
				if (c.minesAround(s, z + 1) == 0) {
					recusivOpen(s, z + 1);
				}
				// Ok
				b4.setText(text);
				b4.setFont(font);
			}
			if (s > -1 && z - 1 > -1 && z - 1 < c.getSizeY()
					&& s < c.getSizeY()) {
				MineButton b5 = field[s][z - 1];
				text = "" + c.minesAround(s, z - 1);
				if (c.minesAround(s, z - 1) == 0) {
					// recusivOpen(s, z-1);
				}
				b5.setText(text);
				b5.setFont(font);
			}
			if (s - 1 > -1 && z > -1 && z < c.getSizeY()
					&& s - 1 < c.getSizeY()) {
				MineButton b6 = field[s - 1][z];
				text = "" + c.minesAround(s - 1, z);
				if (c.minesAround(s - 1, z) == 0) {
					recusivOpen(s - 1, z);
				}
				// OK
				b6.setText(text);
				b6.setFont(font);
			}
			if (s + 1 > -1 && z > -1 && s + 1 < c.getSizeX()
					&& z < c.getSizeY()) {
				MineButton b7 = field[s + 1][z];
				text = "" + c.minesAround(s + 1, z);
				if (c.minesAround(s + 1, z) == 0) {
					// recusivOpen(s+1, z);
				}
				b7.setText(text);
				b7.setFont(font);
			}
			if (s + 1 > -1 && z - 1 > -1 && s + 1 < c.getSizeX()
					&& z - 1 < c.getSizeY()) {
				MineButton b8 = field[s + 1][z - 1];
				text = "" + c.minesAround(s + 1, z - 1);
				if (c.minesAround(s + 1, z - 1) == 0) {
					// recusivOpen(s+1, z-1);
				}
				b8.setText(text);
				b8.setFont(font);
			}
		}
	}
}