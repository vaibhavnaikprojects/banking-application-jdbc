package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class SameAccountException extends Exception {

	public SameAccountException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SameAccountException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public SameAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SameAccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SameAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
