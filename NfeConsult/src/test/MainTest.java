package test;

import java.util.ArrayList;

import com.nfeconsult.controller.NfeController;
import com.nfeconsult.model.NfeModel;
import com.nfeconsult.service.NfeService;
import com.nfeconsult.service.PathService;

public class MainTest {

	public static void main(String[] args) {
		String dir = "C:\\Users\\Thiago\\Downloads\\202309";
		try {
			ArrayList<NfeModel> nfeList = NfeController.getList(dir);
			for (int i = 0; i < nfeList.size(); i++) {
				System.out.println(nfeList.get(i).getCode());				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
