package views.main_frame;

import controller.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WestPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton restartAutomaton, validateWord;

	public WestPanel(ActionListener listener) {
		restartAutomaton = new JButton();
		validateWord = new JButton();
		this.setBackground(new Color(237, 237, 237));
		this.initComponents(listener);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		restartAutomaton.setText("Reset");
		restartAutomaton.addActionListener(actionListener);
		restartAutomaton.setActionCommand(Commands.C_RESTART.toString());
		this.add(restartAutomaton);
		validateWord.addActionListener(actionListener);
		validateWord.setActionCommand(Commands.C_VALIDATE_WORD.toString());
		validateWord.setText("Go");
		this.add(validateWord);
	}

}
