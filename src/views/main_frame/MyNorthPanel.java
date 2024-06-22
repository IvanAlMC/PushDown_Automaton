package views.main_frame;

import controller.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyNorthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton transition, erase;
	private JLabel text;
	
	public MyNorthPanel(ActionListener listener) {
		transition = new JButton();
		erase = new JButton();
		text = new JLabel();
		this.setBackground(new Color(237, 237, 237));
		this.initComponents(listener);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		text.setText("                                                                Pushdown Automaton");
		text.setFont(new Font("Cabin", Font.BOLD, 30));
		text.setBounds(0, 0, 100, 50);
		this.add(text, BorderLayout.PAGE_START);
		transition.setText("Transition");
		transition.addActionListener(actionListener);
		transition.setActionCommand(Commands.C_TRANSITION.toString());
		this.add(transition, BorderLayout.SOUTH);
		erase.addActionListener(actionListener);
		erase.setActionCommand(Commands.C_ERASE.toString());
		erase.setText("Delete");
		this.add(erase, BorderLayout.SOUTH);
	}

}
