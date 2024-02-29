package com.github.thiagobritto.nfeconsult.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.thiagobritto.nfeconsult.DAO.FilterListDAO;
import com.github.thiagobritto.nfeconsult.interfaces.ControllerInterface;
import com.github.thiagobritto.nfeconsult.interfaces.FilterListDAOInterface;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;
import com.github.thiagobritto.nfeconsult.services.SendService;

public class FilterListController implements ControllerInterface {

	private FilterListDAOInterface DAO;

	public FilterListController() {
		DAO = new FilterListDAO();
	}
	
	/*
	 * REQUISITANDO A INCERSÃO DE UM ITEM PARA FilterListDAO()*/

	@Override
	public void post(SendService send) {
		Integer id = (Integer) send.getParam(0);
		String desc = (String) send.getParam(1);
		FilterListModel flm = new FilterListModel( id, desc );
	
		// ENVIA O OBJETO PARA SALVAR E TESTA O RETORNO
		if (DAO.save(flm)) {
			// SE CONSEGUIU SALVAR INFORMA PARA A view
			send.setMessage("Salvo com sucesso!").resove();
		} else {
			// SE NAO CONSEGUIU SALVAR INFORMA a view
			send.setMessage("Houve um erro ao salvar o regidtro!").reject();
		}
	}

	@Override
	public void put(SendService send) {
		Integer id = (Integer) send.getParam(0);
		String desc = (String) send.getParam(1);
		FilterListModel flm = new FilterListModel( id, desc );
		
		// ENVIA O OBJETO PARA SER ATUALIZADO E TESTA O RETORNO
		if (DAO.update(flm)) {
			// SE CONSEGUIU ATUALIZAR INFORMA PARA A view
			send.setMessage("Atualizado ok").resove();
		} else {
			// SE NAO CONSEGUIU ATUALIZAR INFORMA a view
			send.setMessage("Houve um erro ao atualizar").reject();
		}
	}

	@Override
	public void delete(SendService send) {
		Integer id = (Integer) send.getParam(0);
		
		// ENVIA O ID PARA SER DELETADO E TESTA O RETORNO
		if (DAO.delete(id)) {
			// SE CONSEGUIU DELETAR INFORMA PARA A view
			send.setMessage("Deletado ok").resove();
		} else {
			// SE NAO CONSEGUIU DELETAR INFORMA a view
			send.setMessage("Houve um erro ao deletar").reject();
		}
	}

	@Override
	public FilterListModel get(Integer id) {
		return DAO.show(id);
	}

	@Override
	public ArrayList<FilterListModel> get() {
		return DAO.show();
	}

}
