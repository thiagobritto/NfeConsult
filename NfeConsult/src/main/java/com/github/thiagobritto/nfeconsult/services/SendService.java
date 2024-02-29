package com.github.thiagobritto.nfeconsult.services;

import java.util.Objects;

public class SendService {

	private Object[] params;
	private String message;
	private Integer status;
	private Object data;

	public SendService(Object... obj) {
		this.params = obj;
	}

	public SendService setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public SendService setMessage(String message) {
		this.message = message;
		return this;
	}

	public SendService setData(Object data) {
		this.data = data;
		return this;
	}

	public Object[] getParams() {
		return this.params;
	}

	public Object getParam(Integer key) {
		return this.params[key];
	}

	// SUB CLASS RESPONSE
	public class Response {

		public Integer getStatus() {
			return SendService.this.status;
		}

		public String getMessage() {
			return SendService.this.message;
		}

		public Object getData() {
			return SendService.this.data;
		}

		public Object[] getParams() {
			return SendService.this.params;
		}

		public Object getParam(Integer key) {
			return SendService.this.params[key];
		}

	}

	// METODOS A SEREM REESCRITOS
	public void resove() {

	}

	public void reject() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(data, message, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SendService other = (SendService) obj;
		return Objects.equals(data, other.data) && Objects.equals(message, other.message)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Call [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

}