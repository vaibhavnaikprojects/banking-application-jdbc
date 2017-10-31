package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class RetryLimitExceededException extends Exception {

	public RetryLimitExceededException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RetryLimitExceededException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RetryLimitExceededException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RetryLimitExceededException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
