package com.github.thiagobritto.nfeconsult.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.thiagobritto.nfeconsult.interfaces.ControllerInterface;
import com.github.thiagobritto.nfeconsult.interfaces.DAOInterface;
import com.github.thiagobritto.nfeconsult.interfaces.Promise;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;

abstract class Controller implements ControllerInterface {
	
	protected HashMap<Integer, Object> DBL = new HashMap<Integer, Object>();
	protected DAOInterface DAO;
	
	Controller(){
		// ATRIBUI O DAO A class
		DAO = assignDAO();
	}
	
	// OBRIGA AS SUB CLASSES A IMPLEMENTAR OS METODOS ABSTRATOS
	protected abstract DAOInterface assignDAO();
	protected abstract Integer castingToModelAndReturnId(Object obj);
	
	@Override
	public void post(Object obj, Promise res) {
		// ENVIA O OBJETO PARA SALVAR E TESTA O RETORNO
		if (DAO.save(obj)) {
			// SE CONSEGUIU SALVAR INFORMA PARA A view
			res.resove("Salvo com sucesso!");
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU SALVAR INFORMA a view
			res.reject("Houve um erro ao salvar o regidtro!");
		}
	}

	@Override
	public void put(Object obj, Promise res) {
		// ENVIA O OBJETO PARA SER ATUALIZADO E TESTA O RETORNO
		if(DAO.update(obj)) {
			// SE CONSEGUIU ATUALIZAR INFORMA PARA A view 
			res.resove("Atualizado ok");
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU ATUALIZAR INFORMA a view 
			res.reject("Houve um erro ao atualizar");
		}
	}

	@Override
	public void delete(Integer id, Promise res) {
		// ENVIA O ID PARA SER DELETADO E TESTA O RETORNO
		if(DAO.delete(id)) {
			// SE CONSEGUIU DELETAR INFORMA PARA A view 
			res.resove("Deletado ok");
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		} else {
			// SE NAO CONSEGUIU DELETAR INFORMA a view 
			res.reject("Houve um erro ao deletar");
		}
	}

	@Override
	public Object get(Integer id) {
		// TESTA SE TENHO O Object NA MEMORIA
		if (DBL.containsKey(id))
			// SE TEM O Object EM MEMORIA RETORNA PARA A view
			return DBL.get(id);
		// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
		upDBL();
		// TESTA SE TENHO O Object NA MEMORIA
		if (DBL.containsKey(id))
			// SE TEM O Object EM MEMORIA RETORNA PARA A view
			return DBL.get(id);
		// RETORNA NULL
		return null;
	}

	@Override
	public ArrayList<Object> get() {
		// TESTA SE A MEMORIA ESTA LIMPA
		if (DBL.isEmpty()) 
			// ATUALIZA TODOS OS DADOS VINDOS DO BANCO NA MEMORIA LOGICA
			upDBL();
		// RETORNA OS DADOS PARA A view
		return (ArrayList<Object>) DBL.values();
	}
	
	protected void upDBL() {
		// LIMPA O BANCO DE DADOS
		DBL.clear();
		// TRAZ TODOS OS DADOS DO BANCO EM UM ArrayList<Object> E VARRE-O
		DAO.show().forEach(obj -> {
			// ADICIONA O Object A UM HashMap<Object> CHAMADO 'DBL' 
			// COM A PRIMARY KEY DO BANCO SENDO REFLETIDA NA KEY DE 'DBL'
			DBL.put(castingToModelAndReturnId(obj), obj);
		});
	}
}
