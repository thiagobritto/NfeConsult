package com.github.thiagobritto.nfeconsult.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.thiagobritto.nfeconsult.interfaces.DAOInterface;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;

public class FilterListDAO implements DAOInterface {

	private PreparedStatement prepare;
	private ResultSet result;
	private Connection con;

	private final String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS filter_list (" 
		+ "id INTEGER, "
		+ "name TEXT NOT NULL UNIQUE, "
		+ "PRIMARY KEY (id AUTOINCREMENT)"
	+ ");";

	private final String CREATE_ITEM = 
		"INSERT INTO filter_list (id, name) VALUES (NULL, ?)";

	private final String READ_ITEM = 
		"SELECT * FROM filter_list WHERE id = ?";
	
	private final String READ_ALL_ITEM = 
			"SELECT * FROM filter_list";

	private final String UPDATE_ITEM = 
		"UPDATE filter_list name = ? WHERE id = ?";

	private final String DELETE_ITEM = 
		"DELETE FROM filter_list WHERE id = ?";

	public FilterListDAO() {	
		con = Conexao.getInstance().getConnection();
		startTable();
	}

	private void startTable() {
		try {
			con.createStatement().executeUpdate(CREATE_TABLE);
		} catch (SQLException e) {
			System.out.println("Houve um erro ao criar a tabela: 'filter_list'");
			e.printStackTrace();
		}
	}
	
	@Override
	public Boolean save(Object model) {
		FilterListModel md = (FilterListModel) model;
		try {
			prepare = con.prepareStatement(CREATE_ITEM);
			prepare.setString(1, md.getDescription());
			int res = prepare.executeUpdate();
			if(res>0)return true;
		} catch (SQLException e) {
			System.out.println("Houve um erro ao inserir um item em: filter_list.");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean update(Object model) {
		FilterListModel md = (FilterListModel) model;
		try {
			prepare = con.prepareStatement(UPDATE_ITEM);
			prepare.setString(1, md.getDescription());
			prepare.setInt(2, md.getId());
			int res = prepare.executeUpdate();
			if(res>0)return true;
		} catch (SQLException e) {
			System.out.println("Houve um erro ao atualizar o item "+md.getId()+" em: filter_list.");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean delete(Integer id) {	
		try {
			prepare = con.prepareStatement(DELETE_ITEM);
			prepare.setInt(1, id);
			int res = prepare.executeUpdate();
			if(res>0)return true;
		} catch (SQLException e) {
			System.out.println("Houve um erro ao deletar o item "+id+" em: filter_list.");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Object show(Integer id) {
		try {
			prepare = con.prepareStatement(READ_ITEM);
			prepare.setInt(1, id);
			result = prepare.executeQuery();
			if(result.next())
				return new FilterListModel(result.getInt("id"), result.getString("name"));
			
		} catch (SQLException e) {
			System.out.println("Houve um erro ao buscar o item "+id+" em: filter_list.");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<Object> show(){
		try {
			result = con.createStatement().executeQuery(READ_ALL_ITEM);
			ArrayList<Object> listItemModel = new ArrayList<Object>();
			while (result.next()) {
				listItemModel.add(
					new FilterListModel(result.getInt("id"), result.getString("name"))
				);
			}
			if(listItemModel.size()>0)
				return listItemModel;
			
		} catch (SQLException e) {
			System.out.println("Holve um erro ao listar todos os  itens");
			e.printStackTrace();
		}
		return null;
	}	
}
