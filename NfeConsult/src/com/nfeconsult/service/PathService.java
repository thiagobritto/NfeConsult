package com.nfeconsult.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PathService {
	
	public static ArrayList<String> findFilesXML(File dirPath) {
		
		ArrayList<String> listPath = new ArrayList<String>();
		
		File[] listFiles = dirPath.listFiles();
		if(listFiles != null)
			for(File x: listFiles) 
				if(x.getName().endsWith(".xml")) listPath.add(x.getPath());
	
		return listPath;
	}
	
	public static ArrayList<String> findFilesXMLr(File dirPath) {
		
		ArrayList<String> listPath = new ArrayList<String>();
		
		File[] listFiles = dirPath.listFiles();
		if(listFiles != null) {			
			for(File x: listFiles) {
				if(x.getName().endsWith(".xml")) listPath.add(x.getPath());
				if(x.isDirectory()) listPath.addAll(findFilesXMLr(new File(x.getPath())));
			}
		}
		return listPath;
	}
}
