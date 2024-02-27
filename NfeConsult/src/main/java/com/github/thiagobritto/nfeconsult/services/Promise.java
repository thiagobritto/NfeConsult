package com.github.thiagobritto.nfeconsult.services;

import java.util.ArrayList;

public class Promise {
	private Object data;
	private String message;
	private Integer status;
	
	public Promise setData(Object data) {
		this.data = data;
		return this;
	}
	public Promise setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public Promise setMessage(String message) {
		this.message = message;
		return this;
	}
	public Object getData() {
		return this.data;
	}
	public ArrayList<?> getArrayData() {
		return (ArrayList<?>) this.data;
	}
	public Integer getStatus() {
		return this.status;
	}
	public String getMessage() {
		return this.message;
	}
	public void resove(Promise res) {
		
	}
	public void reject(Promise res) {
		
	}
	
}
