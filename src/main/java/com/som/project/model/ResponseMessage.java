package com.som.project.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
	
	private Integer statusCode;
	private String status;
	private String message;
	
	private Object data;
	
	private List<?> list;

	public ResponseMessage(Integer statusCode, String status, String message, Object data) {
		super();
		this.setStatusCode(statusCode);
		this.setStatus(status);
		this.setMessage(message);
		this.setData(data);
	}

	public ResponseMessage(Integer statusCode, String status, String message) {
		super();
		this.setStatusCode(statusCode);
		this.setStatus(status);
		this.setMessage(message);
	}

	public ResponseMessage(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.setStatusCode(statusCode);
		this.setStatus(status);
		this.setMessage(message);
		this.list = list;
	}
	
	
//setter and getter if sometimes lmbok not work
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



}
