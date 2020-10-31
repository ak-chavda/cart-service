package com.example.cart.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public NotFoundException() {
		super();
		msg=new String();
	}

	public NotFoundException(String msg) {
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
