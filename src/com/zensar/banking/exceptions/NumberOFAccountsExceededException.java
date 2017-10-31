package com.zensar.banking.exceptions;
@SuppressWarnings("serial")
public class NumberOFAccountsExceededException extends Exception{
	public NumberOFAccountsExceededException() {super();}
	public NumberOFAccountsExceededException(String message, Throwable cause) {super(message, cause);}
	public NumberOFAccountsExceededException(String message) {super(message);}
	public NumberOFAccountsExceededException(Throwable cause) {	super(cause);}
}