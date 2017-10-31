package com.zensar.banking.client;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.zensar.banking.bankingservices.BankingServices;
import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Transaction;
import com.zensar.banking.exceptions.*;
import com.zensar.banking.providers.BankingServiceProvider;

public class Client {
	public static void main(String[] args){
		int choice=0;	 
		try {
			BankingServices bankingServices = BankingServiceProvider.BankingServProvider();
			do{
				try{
					Scanner scanner=new Scanner(System.in);	
					int customerId=0,accountNo=0,amount=0,pin=0;
					System.out.println("\n-------ZENSAR BANKING SYSTEM-------\n1.CUSTOMER REGISTRATION\n2.OPEN ACCOUNT\n3.DEPOSIT\n4.WITHDRAWL\n5.FUND TRANSFER\n6.BALANCE ENQUIRY\n7.ACCOUNT DETAILS\n8.MY ACCOUNTS\n9.MINISTATEMENT\n10.REGENRATE PIN\n11.CHANGE PIN\n12.DELETE ACCOUNT\n13.DELETE CUSTOMER\n14.EXIT\n----------------------\nSELECT YOUR OPTION");
					choice=scanner.nextInt();
					switch(choice){
					case 1:
						System.out.println("---------CUSTOMER REGISTRATION--------");
						System.out.println("enter Name");
						String custName=scanner.next();
						System.out.println("enter home address city");
						String homeAddressCity=scanner.next();
						System.out.println("enter home address state");
						String homeAddressState=scanner.next();
						System.out.println("enter home address pincode");
						int homeAddressPincode=scanner.nextInt();
						System.out.println("enter local address city");
						String localAddressCity=scanner.next();
						System.out.println("enter local address state");
						String localAddressState=scanner.next();
						System.out.println("enter local address pincode");
						int localAddressPincode=scanner.nextInt();
						System.out.println("Customer created with customer ID "+bankingServices.acceptCustomerDetails(custName,homeAddressCity,homeAddressState,homeAddressPincode,localAddressCity,localAddressState,localAddressPincode));
						break;
					case 2:
						System.out.println("---------OPEN ACCOUNT--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter initial balance");
						amount=scanner.nextInt();
						System.out.println("ACCOUNTS:\n1.SAVINGS\n2.CURRENT");
						int type=scanner.nextInt();
						String accType=null;
						switch(type){
						case 1:accType="SAVINGS";break;
						case 2:accType="CURRENT";break;
						default:System.out.println("ENTER CORRECT CHOICE");
						}				
						int accNo=bankingServices.openAccount(customerId,amount,accType);
						Account account=bankingServices.getAccountDetails(customerId, accNo);
						System.out.println("ACCOUNT NO: "+accNo+" PIN: "+account.getPin());
						break;
					case 3:
						System.out.println("---------DEPOSIT--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						System.out.println("enter amount");
						amount=scanner.nextInt();
						System.out.println("succes with transaction ID "+bankingServices.deposit(customerId,accountNo,pin,amount));
						break;
					case 4:
						System.out.println("---------WITHDRAWL--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						System.out.println("DO YOU WANT TO GET FAVORITE AMOUNT 1:=YES/0:=NO");
						int option=scanner.nextInt();
						if(option==1)	System.out.println("success with transaction ID :"+bankingServices.withdrawWithMemory(customerId,accountNo,pin,option));
						else if(option==0){
							System.out.println("enter amount");
							amount=scanner.nextInt();
							System.out.println("success with transaction ID "+bankingServices.withdraw(customerId,accountNo,pin,amount));
							System.out.println("Do you want to make this amount as FAVOURITE(1:=YES)");
							int favourite=scanner.nextInt();
							if(favourite==1){
								bankingServices.setWithdrawlFavourite(customerId,accountNo,amount);
								System.out.println("Favourite Set");
							}
							else	System.out.println("THANK YOU");
						}
						else	System.out.println("Enter Correct Choice");					
						break;
					case 5:
						System.out.println("---------FUND TRANSFER--------");
						System.out.println("enter customer ID FROM");
						int customerIdFrom=scanner.nextInt();
						System.out.println("enter Account no FROM");
						int accountNoFrom=scanner.nextInt();
						System.out.println("Enter Pin from");
						int pinX=scanner.nextInt();
						System.out.println("enter customer ID TO");
						int customerIdTo=scanner.nextInt();
						System.out.println("enter Account no TO");
						int accountNoTo=scanner.nextInt();
						System.out.println("enter amount");
						amount=scanner.nextInt();
						System.out.println("FUND TRANSFER STATUS : "+bankingServices.fundTransfer(customerIdFrom,accountNoFrom,pinX,customerIdTo,accountNoTo,amount));
						break;
					case 6:
						System.out.println("---------BALANCE ENQUIRY--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						int balance=0;
						balance = bankingServices.getAccountBalance(customerId,accountNo,pin);
						System.out.println("BALANCE="+balance);
						break;
					case 7:
						System.out.println("---------ACCOUNT DETAILS--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						System.out.println(bankingServices.getAccountDetails(customerId,accountNo,pin));
						break;
					case 8:
						System.out.println("---------MY ACCOUNTS--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						ArrayList<Account> accounts=new ArrayList<Account>(bankingServices.getAllAccountsDetails(customerId).values());	
						for (Account account1 : accounts) {
							System.out.println(account1);
						}
						break;
					case 9:
						System.out.println("---------MINISTATEMENT--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						ArrayList<Transaction> transactions = bankingServices.getAllTransactionDetails(customerId,accountNo,pin);
						System.out.println("transactionID\tTransactionAmount\tTransactionType");
						for (Transaction transaction : transactions) {
							System.out.println(transaction);
						}
						break;
					case 10:
						System.out.println("---------REGENERATE PIN--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("NEW PIN :"+bankingServices.regeneratePin(customerId,accountNo));
						break;
					case 11:
						System.out.println("---------CHANGE PIN--------");
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						System.out.println("Enter NEW Pin");
						int pinx=scanner.nextInt();
						System.out.println("CONFIRM Pin");
						int pinY=scanner.nextInt();
						System.out.println("PIN CHANGED,NEW PIN :"+bankingServices.changePin(customerId,accountNo,pin,pinx,pinY));
						break;
					case 12:
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("enter Account no");
						accountNo=scanner.nextInt();
						System.out.println("Enter Pin");
						pin=scanner.nextInt();
						if(bankingServices.deleteCustomerAccount(customerId,accountNo,pin))	System.out.println("ACCOUNT DELETED");
						break;
					case 13:
						System.out.println("enter customer ID");
						customerId=scanner.nextInt();
						System.out.println("Are you sure you want to Continue : (1:= YES 0:=NO)");
						int check=scanner.nextInt();
						if(check==1)	if(bankingServices.deleteCustomer(customerId))	System.out.println("CUSTOMER DELETED");
						else if(check==0)	System.out.println("GOOD CHOICE");
						else	System.out.println("Enter correct choice");
						break;
					case 14:
						bankingServices.closeBankingServices();
						System.out.println("DATA SAVED,VISIT AGAIN");
						System.exit(0);
						break;
					default:
						System.out.println("Enter Correct Option");
						break;
					}
				} catch (InvalidCustomerIdException e) {
					System.out.println(e.getMessage());
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InvalidAccountNoException e) {
					System.out.println(e.getMessage());
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ServicesNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InvalidAmountException e) {
					System.out.println(e.getMessage());
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				} catch (InvalidAccountTypeException e) {
					System.out.println(e.getMessage());
				} catch (NumberOFAccountsExceededException e) {
					System.out.println(e.getMessage());
				} catch (InvalidPincodeException e) {
					System.out.println(e.getMessage());
				}  catch (SameAccountException e) {
					System.out.println(e.getMessage());
				} catch (AccountBlockException e) {
					System.out.println(e.getMessage());
				} catch (IncorrectPinException e) {
					System.out.println(e.getMessage());
				} catch (EnterCorrectionOptionException e) {
					System.out.println(e.getMessage());
				} catch (NoMemoryWithdrawlException e) {
					System.out.println(e.getMessage());
				} catch(InputMismatchException e){
					System.out.println("Enter Valid Number");
				} catch (PinNotMatchException e) {
					System.out.println(e.getMessage());
				} catch (InvalidPinCountException e) {
					System.out.println(e.getMessage());
				}catch(Exception e){
					System.out.println("CHECK LATER");
				}
			}while(choice!=15);
		} catch (ServicesNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}
}
