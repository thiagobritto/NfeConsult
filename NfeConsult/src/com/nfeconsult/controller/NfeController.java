package com.nfeconsult.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.nfeconsult.model.NfeModel;
import com.nfeconsult.model.ProductModel;
import com.nfeconsult.service.NfeService;
import com.nfeconsult.service.PathService;
import com.nfeconsult.view.DialogException;

public class NfeController {

	private static ArrayList<NfeModel> nfeList;

	public static ArrayList<NfeModel> searchNfes(String dir) throws DialogException {
		File path = new File(dir.trim());

		if (!path.isDirectory())
			throw new DialogException("Esse diretorio não é valido!");

		ArrayList<String> nfeListPath = PathService.findFilesXMLr(path);
		nfeList = new ArrayList<NfeModel>();
		for (int i = 0; i < nfeListPath.size(); i++) {
			NfeModel nfe = NfeService.readXML(new NfeModel(), nfeListPath.get(i));
			if (nfe == null)
				continue;
			nfeList.add(nfe);
		}

		if (nfeList.size() < 1)
			throw new DialogException("Nenhum arquivo foi encontrado!");

		return nfeList;
	}

}
