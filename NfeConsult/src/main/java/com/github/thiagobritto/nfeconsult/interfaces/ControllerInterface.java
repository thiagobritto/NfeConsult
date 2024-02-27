package com.github.thiagobritto.nfeconsult.interfaces;

import java.util.ArrayList;

import com.github.thiagobritto.nfeconsult.services.Promise;

public interface ControllerInterface {
	public void post(Object obj, Promise res);
	public void put(Object obj, Promise res);
	public void delete(Integer id, Promise res);
	public Object get(Integer id);
	public ArrayList<?> get();
}
