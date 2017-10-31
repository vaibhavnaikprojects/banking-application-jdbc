package com.zensar.banking.bankingdaoservices;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.*;
import com.zensar.banking.beans.*;
import com.zensar.banking.exceptions.ServicesNotFoundException;
public class CollectionBankingDAOServicesImpl implements BankingDAOServices{
	static HashMap<Integer,Customer> customers;
	File customerDataFile=new File(".\\resources\\CustomerData.txt");
	File counterDetails=new File(".\\resources\\CounterData.txt");
	static int ACCOUNT_NO,CUSTOMER_ID,TRANSACTION_ID;
	public CollectionBankingDAOServicesImpl() throws ServicesNotFoundException {
		if(!customerDataFile.exists()&&!counterDetails.exists()){
			customers=new HashMap<Integer,Customer>();
			CUSTOMER_ID=100;
			ACCOUNT_NO=1000;
			TRANSACTION_ID=5000;
		}
		else{
			try {
				ObjectInputStream stream=new ObjectInputStream(new FileInputStream(customerDataFile));
				customers=(HashMap<Integer,Customer>)stream.readObject();
				stream=new ObjectInputStream(new FileInputStream(counterDetails));
				ArrayList<Integer> list=(ArrayList<Integer>)stream.readObject();
				CUSTOMER_ID=list.get(0);
				ACCOUNT_NO=list.get(1);
				TRANSACTION_ID=list.get(2);
			} catch (FileNotFoundException e) {
				throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
			} catch (ClassNotFoundException e) {
				throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
			} catch (IOException e) {
				throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
			}
		}
	}
	public int insertCustomer(Customer customer) throws SQLException{
		customer.setCustId(CUSTOMER_ID);
		customers.put(customer.getCustId(),customer);
		return CUSTOMER_ID++;
	}
	public boolean updateCustomer(Customer customer) throws SQLException{
		customers.put(customer.getCustId(),customer);
		return true;
	}
	public int generatePin(){
		return (int)(Math.random()*9000)+1000;
	}
	public int insertAccount(int custId, Account account) throws SQLException{
		account.setAccNo(ACCOUNT_NO);
		account.setPin(generatePin());
		customers.get(custId).getAccounts().put(account.getAccNo(), account);
		return ACCOUNT_NO++;
	}
	public boolean updateAccount(int custId, Account account) throws SQLException{
		getCustomer(custId).getAccounts().put(account.getAccNo(),account);
		return true;
	}
	public boolean insertTransaction(int custId, int accNo,Transaction transaction) {
		transaction.setTransId(TRANSACTION_ID++);
		return customers.get(custId).getAccounts().get(accNo).getTransactions().add(transaction);
	}
	public Customer getCustomer(int custId) throws SQLException{
		return customers.get(custId);
	}
	public Account getAccount(int custId, int accNo) throws SQLException{
		return getCustomer(custId).getAccounts().get(accNo);
	}
	public HashMap<Integer, Customer> getCustomers() throws SQLException{
		return customers;
	}
	public HashMap<Integer, Account> getAccounts(int custId) throws SQLException{
		return customers.get(custId).getAccounts();
	}
	public ArrayList<Transaction> getTransactions(int custId, int accNo) throws SQLException{
		return getAccount(custId,accNo).getTransaction();
	}
	public int getTransactionId() throws SQLException{
		return TRANSACTION_ID;
	}
	public boolean deleteCustomer(int custId) throws SQLException{
		customers.remove(custId);
		return true;
	}
	public boolean deleteAccount(int custId, int accNo) throws SQLException{
		customers.get(custId).getAccounts().remove(accNo);
		return true;
	}
	public boolean closeBankingDaoServices() throws ServicesNotFoundException,SQLException{
		try {
			ObjectOutputStream stream;
			stream=new ObjectOutputStream(new FileOutputStream(customerDataFile));
			stream.writeObject(customers);
			stream.close();
			stream=new ObjectOutputStream(new FileOutputStream(counterDetails));
			ArrayList<Integer> list=new ArrayList<Integer>();
			list.add(CUSTOMER_ID);
			list.add(ACCOUNT_NO);
			list.add(TRANSACTION_ID);
			stream.writeObject(list);
			stream.close();
		} catch (SecurityException e) {
			throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
		} catch (FileNotFoundException e) {
			throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
		} catch (IOException e) {
			throw new ServicesNotFoundException("SERVICES ARE UNAVAILABLE",e);
		}
		return true;
	}
}