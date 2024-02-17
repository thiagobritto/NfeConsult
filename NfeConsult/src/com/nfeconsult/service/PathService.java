package com.nfeconsult.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PathService {
	
	public static ArrayList<String> findFilesXML(String dir) throws IOException {
		File dirPath = new File(dir);

		if(!dirPath.isDirectory())
				throw new IOException("Diretorio m~so encontrsdo!");
		
		ArrayList<String> listPath = new ArrayList<String>();
		
		for(File x: dirPath.listFiles()) 
			if(x.getName().endsWith(".xml")) listPath.add(x.getPath());
	
		return listPath;
	}
	
	public static ArrayList<String> findFilesXMLr(String dir) throws IOException {
		File dirPath = new File(dir);

		if(!dirPath.isDirectory())
				throw new IOException("Diretorio m~so encontrsdo!");
		
		ArrayList<String> listPath = new ArrayList<String>();
		
		for(File x: dirPath.listFiles()) {
			if(x.getName().endsWith(".xml")) listPath.add(x.getPath());
			if(x.isDirectory()) listPath.addAll(findFilesXMLr(x.getPath()));
		}
		return listPath;
	}
}
