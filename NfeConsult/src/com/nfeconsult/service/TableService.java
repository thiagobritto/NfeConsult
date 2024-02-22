package com.nfeconsult.service;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.nfeconsult.model.NfeModel;

public class TableService {

	public static void preencherTabela(DefaultTableModel modelo, ArrayList<NfeModel> nfe) {
		modelo.setNumRows(0);

		for (NfeModel c : nfe) {
			modelo.addRow(new Object[] { c.getCode(), 
					c.getClient().getName(), c.getClient().getCode() });
		}
	}

}
