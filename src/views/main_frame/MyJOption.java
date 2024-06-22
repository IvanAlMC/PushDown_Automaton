package views.main_frame;

import javax.swing.*;

public class MyJOption {
	
	public MyJOption() {
		// TODO Auto-generated constructor stub
	}
	
	public int myMenu() {
		String[] opciones = {"Inicial","Final"};
		return JOptionPane.showOptionDialog(null, "Seleciona una opcion","Opcion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
	}
	
	public String myWord(String text) {
		return JOptionPane.showInputDialog(null,text);
	}

}
