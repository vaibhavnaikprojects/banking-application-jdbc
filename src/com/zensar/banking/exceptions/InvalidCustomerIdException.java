package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class InvalidCustomerIdException extends Exception {
	public InvalidCustomerIdException() {super();}
	public InvalidCustomerIdException(String message, Throwable cause) {super(message, cause);}
	public InvalidCustomerIdException(String message) {	super(message);}
	public InvalidCustomerIdException(Throwable cause) {super(cause);}
}