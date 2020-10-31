package com.example.cart.exception;

public class AlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public AlreadyExistsException() {
		super();
		msg = new String();

	}

	public AlreadyExistsException(String msg) {
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
