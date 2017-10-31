package com.zensar.banking.bankingdaoservices;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Customer;
import com.zensar.banking.beans.Transaction;
import com.zensar.banking.exceptions.ServicesNotFoundException;
public interface BankingDAOServices {
	int insertCustomer(Customer customer) throws SQLException;
	int insertAccount(int custId,Account account) throws SQLException;
	boolean updateAccount(int custId, Account account) throws SQLException;
	boolean insertTransaction(int custId,int accNo,Transaction transaction ) throws SQLException;
	Customer getCustomer(int custId) throws SQLException;
	Account getAccount(int custId,int accNo) throws SQLException;
	HashMap<Integer, Customer> getCustomers() throws SQLException;
	HashMap<Integer, Account> getAccounts(int custId) throws SQLException;
	ArrayList<Transaction> getTransactions(int custId,int accNo) throws SQLException;
	boolean deleteCustomer(int custId) throws SQLException;
	boolean deleteAccount(int custId,int accNo) throws SQLException;
	public boolean closeBankingDaoServices() throws ServicesNotFoundException,SQLException;
	public int generatePin();
	public int getTransactionId() throws SQLException;
}
