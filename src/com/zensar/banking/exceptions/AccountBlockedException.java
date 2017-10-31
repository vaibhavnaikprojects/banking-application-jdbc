package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class AccountBlockedException extends Exception {
	public AccountBlockedException() {super();}
	public AccountBlockedException(String message, Throwable cause) {super(message, cause);}
	public AccountBlockedException(String message) {super(message);}
	public AccountBlockedException(Throwable cause) {super(cause);}
}