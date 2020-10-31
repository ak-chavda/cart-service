package com.example.cart.exception;

public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public InternalServerException() {
		super();
		msg = new String();
	}

	public InternalServerException(String msg) {
		this();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
