package com.github.thiagobritto.nfeconsult.interfaces;

import java.util.ArrayList;

import com.github.thiagobritto.nfeconsult.services.SendService;

public interface ControllerInterface {
	
	public void post(SendService send);
	public void put(SendService send);
	public void delete(SendService send);
	public Object get(Integer id);
	public ArrayList<?> get();

}
