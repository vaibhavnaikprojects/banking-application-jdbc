package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException() {super();}
	public InsufficientBalanceException(String message, Throwable cause) {super(message, cause);}
	public InsufficientBalanceException(String message) {super(message);}
	public InsufficientBalanceException(Throwable cause) {super(cause);}
}