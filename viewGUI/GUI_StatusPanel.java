package src.viewGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.model.Flag;

public class GUI_StatusPanel extends JPanel {
	private static final long serialVersionUID = 8925208068361830249L;
	private final JLabel lf = new JLabel("flag: ");
	private final JLabel num = new JLabel();
	private int a = 0;

	public GUI_StatusPanel() {
		setLayout(null);

		Font font = new Font("Arial", 1, 20);
		lf.setFont(font);
		lf.setBounds(450, 2, 50, 30);
		add(lf);

		Font font2 = new Font("Arial", 1, 18);
		num.setFont(font2);
		num.setBounds(540, 2, 50, 30);
		num.setText("10/" + a);

		num.setForeground(Color.red);
		add(num);

		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 600, 40);
		setVisible(true);
	}
	
	public void setFlag(Flag flag) {
		if (flag == Flag.INCREMENT){
			++a;
		}
		else if(a > 0){
			--a;
		}
		
		num.setText("10/" + a);
	}
}