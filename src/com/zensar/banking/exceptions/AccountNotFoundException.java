package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class AccountNotFoundException extends Exception {
	public AccountNotFoundException() {	super();}
	public AccountNotFoundException(String message, Throwable cause) {super(message, cause);}
	public AccountNotFoundException(String message) {super(message);}
	public AccountNotFoundException(Throwable cause) {super(cause);	}
}