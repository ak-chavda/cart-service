package com.example.cart.exception;

public class PayloadException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg;

	PayloadException(){
		super();
		msg=new String();
	}
	
	public PayloadException(String msg) {
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
