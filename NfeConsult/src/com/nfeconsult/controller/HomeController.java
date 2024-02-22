package com.nfeconsult.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.nfeconsult.interfaces.UserDecides;
import com.nfeconsult.model.NfeModel;
import com.nfeconsult.service.NfeService;

public class HomeController {

	public static boolean gerarCSV(String path, ArrayList<NfeModel> listNfeModel, UserDecides user) {
		File file = new File(path);

		if (!file.getName().endsWith(".csv"))
			file = new File(path + ".csv");

		int opt = file.exists() ? user.confirm() : 0;

		if (opt == 0) {

			String csv = NfeService.prepareCSV(listNfeModel);

			try (FileWriter es = new FileWriter(file, StandardCharsets.UTF_8, false)) {

				es.write(csv);
				es.flush();
				return true;
				
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

}
