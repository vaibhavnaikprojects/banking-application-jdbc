package com.zensar.banking.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.zensar.banking.bankingservices.BankingServices;
import com.zensar.banking.exceptions.ServicesNotFoundException;
public class BankingServiceProvider {
	public static BankingServices BankingServProvider() throws ServicesNotFoundException{
		try {
			Properties properties=new Properties();
			properties.load(new FileInputStream(".\\resources\\zensarbankingsystemresources.properties"));
			@SuppressWarnings("rawtypes")
			Class c =Class.forName(properties.getProperty("BankingServices"));
			return (BankingServices)c.newInstance();
		} catch (FileNotFoundException e) {
			throw new ServicesNotFoundException("service not available",e);
		} catch (ClassNotFoundException e) {
			throw new ServicesNotFoundException("service not available",e);
		} catch (InstantiationException e) {
			throw new ServicesNotFoundException("service not available",e);
		} catch (IllegalAccessException e) {
			throw new ServicesNotFoundException("service not available",e);
		} catch (IOException e) {
			throw new ServicesNotFoundException("service not available",e);
		}
	}
}
