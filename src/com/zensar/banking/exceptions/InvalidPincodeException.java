package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidPincodeException extends Exception {
	public InvalidPincodeException() {super();}
	public InvalidPincodeException(String message, Throwable cause) {super(message, cause);}
	public InvalidPincodeException(String message) {super(message);}
	public InvalidPincodeException(Throwable cause) {super(cause);}
}