package controller;

import ndfa.NDFA;
import ndfa.State;
import utils.StateType;
import views.main_frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PushdownControl implements ActionListener {

	private static final String VALIDATION_FAILED = "No se pudo validar Correctamente la palabra";
	private static final String VALIDATION_SUCESS = "Se valido Correctamente la palabra";
	private MainFrame frame; // instancia de la vista, frame principal
	private NDFA finiteAutomata = new NDFA();

	public PushdownControl() {
		frame = new MainFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Commands.valueOf(e.getActionCommand())) {
		case C_RESTART:
			// reiniciar el automata
			frame.restartAutomaton();
			break;
		case C_VALIDATE_WORD:
			// jOption para ingresar la palabra
			this.validateWord();
			break;
		default:
			break;

		}

	}

	public void validateWord() {
		String name = JOptionPane.showInputDialog("Ingresa la palabra a validar");
		if (JOptionPane.showConfirmDialog(frame, "�Estas seguro que desea validar esta palabra: " + name + "?", "Pregunta",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			// llamar al metodo de validacion del modelo y enviar la palabra guardada en
			// name
			boolean validation = finiteAutomata.validateWordDeterministic(name);
			if (validation) {
				this.frame.showMessage(VALIDATION_SUCESS);
			} else {
				this.frame.showMessage(VALIDATION_FAILED);
			}

		}
	}

	public void addState(String stateTag) {
		finiteAutomata.addState(stateTag, StateType.DEFAULT);
	}

	public void updateState(String stateTag, StateType type) {
		finiteAutomata.getState(stateTag).setType(type);
	}

	public void addTransition(String startTag, String endTag, char condition, char pop, char push) {
		State begin = finiteAutomata.getState(startTag);
		State end = finiteAutomata.getState(endTag);
		finiteAutomata.addTransition(condition, pop, push, begin, end);
	}
}
