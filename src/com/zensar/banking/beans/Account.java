package com.zensar.banking.beans;
import java.io.Serializable;
import java.util.ArrayList;
public class Account implements Serializable{
	private int balance;
	private String accType;
	private int accNo;
	private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	private String status;
	private int pin;
	private int counter;
	private int withdrawMemBal;
	public Account() {}
	public Account(int balance, String accType) {
		super();
		this.balance = balance;
		this.accType = accType;
	}
	public Account(int balance, String accType, int accNo) {
		super();
		this.balance = balance;
		this.accType = accType;
		this.accNo = accNo;
		this.status="ENABLED";
	}
	public Account(int balance, String accType, int accNo, String status, int pin,
			int counter, int withdrawMemBal,ArrayList<Transaction> transactions) {
		super();
		this.balance = balance;
		this.accType = accType;
		this.accNo = accNo;
		this.status = status;
		this.pin = pin;
		this.counter = counter;
		this.withdrawMemBal = withdrawMemBal;
		this.transactions = transactions;
	}
	public Account(int balance, String accType, int accNo,String status, int pin,
			int counter, int withdrawMemBal) {
		super();
		this.balance = balance;
		this.accType = accType;
		this.accNo = accNo;
		this.status = status;
		this.pin = pin;
		this.counter = counter;
		this.withdrawMemBal = withdrawMemBal;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int  accNo) {
		this.accNo = accNo;
	}
	public ArrayList<Transaction> getTransaction() {
		return transactions;
	}
	public void setTransaction(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getWithdrawMemBal() {
		return withdrawMemBal;
	}
	public void setWithdrawMemBal(int withdrawMemBal) {
		this.withdrawMemBal = withdrawMemBal;
	}
	public String toString() {
		return "\nAccount [balance=" + balance + ", accType=" + accType
				+ ", accNo=" + accNo +"status=" + status +"\n transactions=\n" + transactions;
	}
	
	
	
	
	
}
