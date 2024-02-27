package com.github.thiagobritto.nfeconsult.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.thiagobritto.nfeconsult.DAO.FilterListDAO;
import com.github.thiagobritto.nfeconsult.interfaces.ControllerInterface;
import com.github.thiagobritto.nfeconsult.interfaces.DAOInterface;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;
import com.github.thiagobritto.nfeconsult.services.Promise;

public class FilterListController implements ControllerInterface {

	private HashMap<Integer, FilterListModel> DBL = new HashMap<Integer, FilterListModel>();
	private DAOInterface DAO;

	FilterListController() {
		DAO = new FilterListDAO();
	}

	@Override
	public void post(Object obj, Promise res) {
		// ENVIA O OBJETO PARA SALVAR E TESTA O RETORNO
		if (DAO.save(obj)) {
			// SE CONSEGUIU SALVAR INFORMA PARA A view
			res.setMessage("Salvo com sucesso!").resove(res);
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU SALVAR INFORMA a view
			res.setMessage("Houve um erro ao salvar o regidtro!").reject(res);
		}
	}

	@Override
	public void put(Object obj, Promise res) {
		// ENVIA O OBJETO PARA SER ATUALIZADO E TESTA O RETORNO
		if (DAO.update(obj)) {
			// SE CONSEGUIU ATUALIZAR INFORMA PARA A view
			res.setMessage("Atualizado ok").resove(res);
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU ATUALIZAR INFORMA a view
			res.setMessage("Houve um erro ao atualizar").reject(res);
		}
	}

	@Override
	public void delete(Integer id, Promise res) {
		// ENVIA O ID PARA SER DELETADO E TESTA O RETORNO
		if (DAO.delete(id)) {
			// SE CONSEGUIU DELETAR INFORMA PARA A view
			res.setMessage("Deletado ok").resove(res);
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU DELETAR INFORMA a view
			res.setMessage("Houve um erro ao deletar").reject(res);
		}
	}

	@Override
	public FilterListModel get(Integer id) {
		// TESTA SE TENHO O Object NA MEMORIA
		if (DBL.containsKey(id))
			// SE TEM O Object EM MEMORIA RETORNA PARA A view
			return (FilterListModel) DBL.get(id);
		// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
		upDBL();
		// TESTA SE TENHO O Object NA MEMORIA
		if (DBL.containsKey(id))
			// SE TEM O Object EM MEMORIA RETORNA PARA A view
			return (FilterListModel) DBL.get(id);
		// RETORNA NULL
		return null;
	}

	@Override
	public ArrayList<FilterListModel> get() {
		// TESTA SE A MEMORIA ESTA LIMPA
		if (DBL.isEmpty())
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		// RETORNA OS DADOS PARA A view
		return (ArrayList<FilterListModel>) DBL.values();
	}

	protected void upDBL() {
		// LIMPA O BANCO DE DADOS
		DBL.clear();
		// TRAZ TODOS OS DADOS DO BANCO EM UM ArrayList<Object> E VARRE-O
		DAO.show().forEach(obj -> {
			// FAZ O casting DO OBJETO PARA O MODELO
			FilterListModel fl = (FilterListModel) obj;
			// ADICIONA O Object A UM HashMap<Object> CHAMADO 'DBL'
			// COM A PRIMARY KEY DO BANCO SENDO REFLETIDA NA KEY DE 'DBL'
			DBL.put(fl.getId(), fl);
		});
	}

}
