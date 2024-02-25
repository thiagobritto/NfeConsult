package com.github.thiagobritto.nfeconsult.interfaces;

import java.util.ArrayList;

public interface DAOInterface {
	public Boolean save(Object model);
	public Boolean update(Object model);
	public Boolean delete(Integer id);
	public Object show(Integer id);
	public ArrayList<Object> show();
}
