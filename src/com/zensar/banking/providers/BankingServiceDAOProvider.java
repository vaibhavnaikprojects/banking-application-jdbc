package com.zensar.banking.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.zensar.banking.bankingdaoservices.BankingDAOServices;
import com.zensar.banking.exceptions.ServicesNotFoundException;

public class BankingServiceDAOProvider {
	public static BankingDAOServices daoServices() throws ServicesNotFoundException{
		try {
			FileInputStream source=new FileInputStream(".\\resources\\zensarbankingsystemresources.properties");
			Properties properties=new Properties();
			properties.load(source);
			@SuppressWarnings("rawtypes")
			Class class1= Class.forName(properties.getProperty("BankingDAOServices"));
			return (BankingDAOServices)class1.newInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new  ServicesNotFoundException("service not available",e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new  ServicesNotFoundException("service not available",e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new  ServicesNotFoundException("service not available",e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new  ServicesNotFoundException("service not available",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new  ServicesNotFoundException("service not available",e);
		}

	}
}
