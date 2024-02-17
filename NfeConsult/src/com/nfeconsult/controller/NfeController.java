package com.nfeconsult.controller;

import java.util.ArrayList;

import com.nfeconsult.model.NfeModel;
import com.nfeconsult.service.NfeService;
import com.nfeconsult.service.PathService;

public class NfeController {
	public static ArrayList<NfeModel> getList(String dir) {
		try {
			ArrayList<String> nfeListPath = PathService.findFilesXMLr(dir);
			ArrayList<NfeModel> nfeList = new ArrayList<NfeModel>();
			
			for (int i = 0; i < nfeListPath.size(); i++) {
				NfeModel nfe = NfeService.readXML(new NfeModel(), nfeListPath.get(i));
				if(nfe==null) continue;
				nfeList.add(nfe);
			}
			return nfeList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
