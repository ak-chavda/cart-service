package com.example.cart.exception;

import java.sql.Timestamp;

public class ExceptionBody {
	/*
	 * "timestamp": "2020-02-27T04:47:05.336+0000", "status": 403, "error":
	 * "Forbidden", "message": "Access Denied",
	 */
	private Timestamp timestamp;
	private int statusCode;
	private String error;
	private String msg;

	public ExceptionBody() {
	}

	public ExceptionBody(Timestamp timestamp, int statusCode, String error, String msg) {
		this();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.error = error;
		this.msg = msg;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
