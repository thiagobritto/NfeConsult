package com.nfeconsult.view;

import javax.swing.JOptionPane;

public class DialogException extends Exception {

	private static final long serialVersionUID = 1L;

	public DialogException(String msg) {
		super(msg);
	}

	public void dialogMessage() {
		JOptionPane.showMessageDialog(null, super.getMessage());
	}
	
	public void dialogMessage(String title, int icon) {
		JOptionPane.showMessageDialog(null, super.getMessage(), title, icon);
	}

}
