package com.github.thiagobritto.nfeconsult.interfaces;

import java.util.ArrayList;

import com.github.thiagobritto.nfeconsult.models.FilterListModel;

public interface FilterListDAOInterface {
	public Integer save(FilterListModel model);
	public Integer update(FilterListModel model);
	public Integer delete(Integer id);
	public FilterListModel show(Integer id);
	public ArrayList<FilterListModel> show();
}
