package com.github.thiagobritto.nfeconsult.interfaces;

import java.util.ArrayList;

import com.github.thiagobritto.nfeconsult.models.FilterListModel;

public interface FilterListDAOInterface {
	public Boolean save(FilterListModel model);
	public Boolean update(FilterListModel model);
	public Boolean delete(Integer id);
	public FilterListModel show(Integer id);
	public ArrayList<FilterListModel> show();
}
