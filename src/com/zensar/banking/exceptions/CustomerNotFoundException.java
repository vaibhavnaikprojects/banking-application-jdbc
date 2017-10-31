package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException() {super();}
	public CustomerNotFoundException(String message, Throwable cause) {	super(message, cause);}
	public CustomerNotFoundException(String message) {super(message);}
	public CustomerNotFoundException(Throwable cause) {	super(cause);}
}