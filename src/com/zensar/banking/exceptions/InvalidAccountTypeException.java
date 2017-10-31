package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidAccountTypeException extends Exception {
	public InvalidAccountTypeException() {super();}
	public InvalidAccountTypeException(String message, Throwable cause) {super(message, cause);}
	public InvalidAccountTypeException(String message) {super(message);}
	public InvalidAccountTypeException(Throwable cause) {super(cause);}
}