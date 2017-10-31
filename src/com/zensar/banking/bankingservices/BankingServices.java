package com.zensar.banking.bankingservices;
import java.util.ArrayList;
import java.util.HashMap;
import com.zensar.banking.beans.*;
import com.zensar.banking.exceptions.*;
public interface BankingServices {
	int acceptCustomerDetails(String custName,String homeAddressCity,String homeAddressState,int homeAddressPincode,String localAddressCity, String localAddressState,int localAddressPincode)
			throws InvalidPincodeException, ServicesNotFoundException;

	int openAccount(int custId,int balance,String accType)
			throws InvalidCustomerIdException,CustomerNotFoundException,
			InvalidAmountException,	InvalidAccountTypeException,ServicesNotFoundException,NumberOFAccountsExceededException;
	int withdraw(int custId, int accNo,int pin,int amt)
			throws InvalidCustomerIdException,CustomerNotFoundException,
			InvalidAccountNoException,AccountNotFoundException,	InvalidAmountException,
			InsufficientBalanceException, ServicesNotFoundException, AccountBlockException,IncorrectPinException;
	boolean fundTransfer(int custIdFrom,int accNoFrom,int pinX,int custIdTo,int accNoTo,int amt)
			throws InvalidCustomerIdException,
			CustomerNotFoundException,InvalidAccountNoException,AccountNotFoundException,
			InvalidAmountException,	InsufficientBalanceException,ServicesNotFoundException, SameAccountException, AccountBlockException,IncorrectPinException;
	int deposit(int custId, int accNo,int amt, int amount)
			throws InvalidCustomerIdException,CustomerNotFoundException,
			InvalidAccountNoException,AccountNotFoundException,
			InvalidAmountException, ServicesNotFoundException, AccountBlockException,IncorrectPinException;
	public boolean deleteCustomer(int customerId)throws CustomerNotFoundException, InvalidCustomerIdException,ServicesNotFoundException;
	public boolean deleteCustomerAccount(int customerId, int accountNo,int pin)throws CustomerNotFoundException, InvalidCustomerIdException,InvalidAccountNoException, AccountNotFoundException,ServicesNotFoundException, IncorrectPinException, AccountBlockException;
	int getAccountBalance(int custId, int accNo,int pin)	throws CustomerNotFoundException,InvalidCustomerIdException,
	InvalidAccountNoException,AccountNotFoundException,ServicesNotFoundException, AccountBlockException,IncorrectPinException;
	Customer  getCustomerDetails(int custId)throws InvalidCustomerIdException,
	CustomerNotFoundException, ServicesNotFoundException;
	Account getAccountDetails(int custId, int accNo,int pin)throws InvalidCustomerIdException,
	CustomerNotFoundException, InvalidAccountNoException,AccountNotFoundException,ServicesNotFoundException, AccountBlockException,IncorrectPinException;
	HashMap<Integer,Account> getAllAccountsDetails(int custId)throws InvalidCustomerIdException,
	CustomerNotFoundException,ServicesNotFoundException;
	ArrayList<Transaction> getAllTransactionDetails(int custId, int accNo,int pin)throws InvalidCustomerIdException,
	CustomerNotFoundException, InvalidAccountNoException,AccountNotFoundException,ServicesNotFoundException, AccountBlockException,IncorrectPinException;;
	boolean closeBankingServices()throws ServicesNotFoundException;
	Account getAccountDetails(int customerId, int accNo)throws InvalidCustomerIdException, CustomerNotFoundException,InvalidAccountNoException, AccountNotFoundException,ServicesNotFoundException;

	public int regeneratePin(int custId,int accNo) throws InvalidCustomerIdException, CustomerNotFoundException, InvalidAccountNoException, AccountNotFoundException, ServicesNotFoundException;
	public int withdrawWithMemory(int custId, int accNo,int pin,int option) throws InvalidCustomerIdException, CustomerNotFoundException,InvalidAccountNoException, AccountNotFoundException,InvalidAmountException, InsufficientBalanceException,ServicesNotFoundException, IncorrectPinException, AccountBlockException, EnterCorrectionOptionException, NoMemoryWithdrawlException;
	public int changePin(int custId,int accNo,int pin,int newPin,int cPin) throws PinNotMatchException, InvalidCustomerIdException, CustomerNotFoundException, InvalidAccountNoException, AccountNotFoundException, ServicesNotFoundException, IncorrectPinException, AccountBlockException,InvalidPinCountException;
	public void setWithdrawlFavourite(int custId,int accNo,int amount) throws InvalidCustomerIdException, CustomerNotFoundException, InvalidAccountNoException, AccountNotFoundException, ServicesNotFoundException;
}
