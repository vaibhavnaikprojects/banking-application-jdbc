package com.zensar.banking.beans;
import java.io.Serializable;
import java.util.HashMap;
public class Customer implements Serializable{
	private int custId;
	private String custName;
	private Address homeAddress,localAddress;
	private HashMap<Integer,Account> accounts=new HashMap<Integer,Account>();
	public Customer() {}
	public Customer( String custName,Address homeAddress, Address localAddress) {
		super();
		this.custName = custName;
		this.homeAddress = homeAddress;
		this.localAddress = localAddress;
	}
	public Customer(int custId, String custName,
			Address homeAddress, Address localAddress) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.homeAddress = homeAddress;
		this.localAddress = localAddress;
	}

	public Customer(int custId, String custName, Address homeAddress,
			Address localAddress, HashMap<Integer, Account> accounts) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.homeAddress = homeAddress;
		this.localAddress = localAddress;
		this.accounts = accounts;
	}
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(Address localAddress) {
		this.localAddress = localAddress;
	}
	public HashMap<Integer, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(HashMap<Integer, Account> accounts) {
		this.accounts = accounts;
	}
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName
				+ ", homeAddress=" + homeAddress + ", localAddress="
				+ localAddress + ", accounts=" + accounts + "]";
	}



}
