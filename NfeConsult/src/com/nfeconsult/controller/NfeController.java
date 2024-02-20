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

	public static ArrayList<NfeModel> 
	filterByProducts(DefaultListModel<String> listProducts) {
		if(listProducts.isEmpty()) return nfeList;
		
		ArrayList<NfeModel> newNfeList = new ArrayList<NfeModel>();
		for (int i = 0; i < nfeList.size(); i++) {
			Boolean econtrou = false;
			ProductModel[] prod = nfeList.get(i).getProducts();
			for (int j = 0; j < prod.length; j++) {
				String desc = prod[j].getDesc();
				for (int x = 0; x < listProducts.size(); x++) {
					if(desc.contains(listProducts.get(x))) {
						econtrou = true;
						break;
					}
				}
								
			}
			if(econtrou) newNfeList.add(nfeList.get(i));
		}
		return newNfeList;
	}
}
