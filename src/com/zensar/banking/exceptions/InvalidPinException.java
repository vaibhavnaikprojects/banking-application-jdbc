package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidPinException extends Exception {public InvalidPinException() {super();}
	public InvalidPinException(String message, Throwable cause) {super(message, cause);}
	public InvalidPinException(String message) {super(message);}
	public InvalidPinException(Throwable cause) {super(cause);}
}