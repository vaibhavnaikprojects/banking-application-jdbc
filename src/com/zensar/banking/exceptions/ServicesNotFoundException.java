package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class ServicesNotFoundException extends Exception {
	public ServicesNotFoundException() {super();}
	public ServicesNotFoundException(String message, Throwable cause) {	super(message, cause);}
	public ServicesNotFoundException(String message) {	super(message);}
	public ServicesNotFoundException(Throwable cause) {	super(cause);}	
}