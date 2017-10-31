package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidAmountException extends Exception {
	public InvalidAmountException() {super();}
	public InvalidAmountException(String message, Throwable cause) {super(message, cause);}
	public InvalidAmountException(String message) {super(message);}
	public InvalidAmountException(Throwable cause) {super(cause);}
}