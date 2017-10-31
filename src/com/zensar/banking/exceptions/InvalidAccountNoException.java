package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidAccountNoException extends Exception {
	public InvalidAccountNoException() {super();}
	public InvalidAccountNoException(String message, Throwable cause) {	super(message, cause);}
	public InvalidAccountNoException(String message) {super(message);}
	public InvalidAccountNoException(Throwable cause) {	super(cause);}
}