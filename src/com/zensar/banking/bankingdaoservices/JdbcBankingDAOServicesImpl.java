package com.zensar.banking.bankingdaoservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Address;
import com.zensar.banking.beans.Customer;
import com.zensar.banking.beans.Transaction;
import com.zensar.banking.exceptions.ServicesNotFoundException;
import com.zensar.banking.providers.BankingConnectionProvider;

public class JdbcBankingDAOServicesImpl implements BankingDAOServices{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public JdbcBankingDAOServicesImpl() throws ServicesNotFoundException{
		try {
			conn=BankingConnectionProvider.getConnection();
			stmt=conn.createStatement();
		} catch (SQLException e) {
			throw new ServicesNotFoundException("SERVICE NOT AVAILABLE",e);
		}
		if(conn!=null)	System.out.println("DATABASE CONNECTED");
		else	System.out.println("DATABASE NOT CONNECTED");
	}
	public int insertCustomer(Customer customer) throws SQLException {
		int custId=0;
		try {
			conn.setAutoCommit(false);
			stmt.executeUpdate("insert into Customer(custName) values('"+customer.getCustName()+"')");
			pstmt=conn.prepareStatement("select max(custId) from Customer");
			rs=pstmt.executeQuery();
			rs.next();
			custId= rs.getInt(1);
			System.out.println(custId);	
			pstmt=conn.prepareStatement("insert into Address(city,state,pincode,custId) values(?,?,?,?)");
			pstmt.setString(1, customer.getHomeAddress().getCity());
			pstmt.setString(2, customer.getHomeAddress().getState());
			pstmt.setInt(3, customer.getHomeAddress().getPincode());
			pstmt.setInt(4,custId);
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement("insert into Address(city,state,pincode,custId) values(?,?,?,?)");
			pstmt.setString(1, customer.getLocalAddress().getCity());
			pstmt.setString(2, customer.getLocalAddress().getState());
			pstmt.setInt(3, customer.getLocalAddress().getPincode());
			pstmt.setInt(4,custId);
			pstmt.executeUpdate();
			conn.commit();
			return custId;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);
			if(pstmt!=null)	pstmt.close();
		}
	}
	public int generatePin(){
		return (int)(Math.random()*9000)+1000;
	}
	public int insertAccount(int custId, Account account) throws SQLException {
		try{
			pstmt=conn.prepareStatement("insert into Account(accType,balance,custId,pin,status) values(?,?,?,?,?)");
			pstmt.setString(1, account.getAccType());
			pstmt.setInt(2, account.getBalance());
			pstmt.setInt(3, custId);
			pstmt.setInt(4, generatePin());
			pstmt.setString(5,"ENABLED");
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement("select max(accNo) from Account");
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
		finally{
			if(rs!=null) rs.close();
			if(pstmt!=null)	pstmt.close();
		}
	}
	public boolean updateAccount(int custId, Account account)throws SQLException {
		try{
			pstmt=conn.prepareStatement("update Account set balance=?,pin=?,withdrawMemBal=?,counter=?,status=? where custId=? and accNo=?");
			pstmt.setInt(1, account.getBalance());
			pstmt.setInt(2, account.getPin());
			pstmt.setInt(3, account.getWithdrawMemBal());
			pstmt.setInt(4, account.getCounter());
			pstmt.setString(5, account.getStatus());
			pstmt.setInt(6,custId);
			pstmt.setInt(7, account.getAccNo());
			pstmt.executeUpdate();
			return true;
		}
		finally{
			if(pstmt!=null)	pstmt.close();
		}
	}
	public boolean insertTransaction(int custId, int accNo,Transaction transaction) throws SQLException {
		try{
			pstmt=conn.prepareStatement("insert into Transaction(transType,transAmt,accNo) values(?,?,?)");
			pstmt.setString(1, transaction.getTransType());
			pstmt.setInt(2, transaction.getTransAmt());
			pstmt.setInt(3, accNo);
			pstmt.executeUpdate();
			return true;
		}
		finally{
			if(pstmt!=null)	pstmt.close();
		}
	}
	public int getTransactionId() throws SQLException{
		try{
			pstmt=conn.prepareStatement("select max(transId) from Transaction");
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
		finally{
			if(rs!=null)	rs.close();
			if(pstmt!=null)	pstmt.close();
		}
	}
	public Customer getCustomer(int custId) throws SQLException {
		ResultSet rs1=null;
		try{
			pstmt=conn.prepareStatement("select city,state,pincode from Customer c,Address a where c.custId=a.custId and c.custId=?");
			pstmt.setInt(1, custId);
			rs1=pstmt.executeQuery();
			Address[] addresses=new Address[2];
			for(int i=0;rs1.next();i++){
				addresses[i]=new Address(rs1.getString("city"),rs1.getString("state"),rs1.getInt("pincode"));
			}
			pstmt=conn.prepareStatement("select * from Customer where custId=?");
			pstmt.setInt(1, custId);
			rs1=pstmt.executeQuery();
			if(rs1.next())	return new Customer(custId, rs1.getString("custName"), addresses[0], addresses[1],getAccounts(custId));
			return null;
		}
		finally{
			if(pstmt!=null)	pstmt.close();
			if(rs1!=null) rs1.close();
		}
	}
	public Account getAccount(int custId, int accNo) throws SQLException {
		try{
			ArrayList<Transaction> transactions=getTransactions(custId, accNo);
			pstmt=conn.prepareStatement("select * from Account where custId=? and accNo=?");
			pstmt.setInt(1, custId);
			pstmt.setInt(2, accNo);
			rs=pstmt.executeQuery();
			if(rs.next())	return new Account(rs.getInt("balance"),rs.getString("accType"),rs.getInt("accNo"),rs.getString("status"),rs.getInt("pin"),rs.getInt("counter"),rs.getInt("withdrawMemBal"),transactions);
			return null;
		}
		finally{
			if(pstmt!=null)	pstmt.close();
			if(rs!=null) rs.close();
		}
	}
	public HashMap<Integer, Customer> getCustomers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public HashMap<Integer, Account> getAccounts(int custId) throws SQLException {
		ResultSet rs1=null;
		try{
			pstmt=conn.prepareStatement("select * from Account where custId=?");
			pstmt.setInt(1, custId);
			rs1=pstmt.executeQuery();
			HashMap<Integer,Account> accounts =new HashMap<Integer,Account>();
			while(rs1.next()){
				ArrayList<Transaction> transactions=getTransactions(custId, rs1.getInt("accNo"));
				accounts.put(rs1.getInt("accNo"), new Account(rs1.getInt("balance"),rs1.getString("accType"),rs1.getInt("accNo"),rs1.getString("status"),rs1.getInt("pin"),rs1.getInt("counter"),rs1.getInt("withdrawMemBal"),transactions));
			}
			return accounts;
		}
		finally{
			if(pstmt!=null)	pstmt.close();
			if(rs1!=null) rs1.close();
		}
	}
	public ArrayList<Transaction> getTransactions(int custId, int accNo) throws SQLException {
		try{
			pstmt=conn.prepareStatement("select * from Transaction where accNo=?");
			pstmt.setInt(1, accNo);
			rs=pstmt.executeQuery();
			ArrayList<Transaction> transactions=new ArrayList<Transaction>();
			while(rs.next()){
				transactions.add(new Transaction(rs.getInt("transId"), rs.getInt("transAmt"),rs.getString("transType")));
			}
			return transactions;
		}finally{
			if(pstmt!=null)	pstmt.close();
			if(rs!=null) rs.close();
		}
	}
	public boolean deleteCustomer(int custId) throws SQLException {
		try{
			pstmt=conn.prepareStatement("delete from Customer where custId=?");
			pstmt.setInt(1, custId);
			pstmt.executeUpdate();
			return true;
		}finally{
			if(pstmt!=null)	pstmt.close();
		}
	}
	public boolean deleteAccount(int custId, int accNo) throws SQLException {
		try{
			pstmt=conn.prepareStatement("delete from account where accNo=?");
			pstmt.setInt(1, accNo);
			pstmt.executeUpdate();
			return true;
		}finally{
			if(pstmt!=null)	pstmt.close();
		}
	}
	public boolean closeBankingDaoServices() throws ServicesNotFoundException,SQLException {
		conn.close();
		conn=null;
		return true;
	}
}
