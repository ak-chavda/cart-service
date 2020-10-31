package com.example.cart.exception;

public class NoContentFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public NoContentFound() {
		super();
		msg = new String();
	}

	public NoContentFound(String msg) {
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
