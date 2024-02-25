package com.github.thiagobritto.nfeconsult.controllers;

import com.github.thiagobritto.nfeconsult.DAO.FilterListDAO;
import com.github.thiagobritto.nfeconsult.interfaces.DAOInterface;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;

public class FilterListController extends Controller {

	@Override
	protected DAOInterface assignDAO() {
		return new FilterListDAO();
	}

	@Override
	protected Integer castingToModelAndReturnId(Object obj) {
		// FAZ UM CASTING PARA O Object MODELO E RETORNA O ID
		return ((FilterListModel) obj).getId();
	}

}
