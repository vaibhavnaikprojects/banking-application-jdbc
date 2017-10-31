package com.zensar.banking.providers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zensar.banking.exceptions.ServicesNotFoundException;

public class BankingConnectionProvider {
	private static Connection connection;
	public static Connection getConnection() throws ServicesNotFoundException{
		try {
			if(connection==null){
			Properties properties=new Properties();
			properties.load(new FileInputStream(".\\resources\\zensarbankingsystemresources.properties"));
			Class.forName(properties.getProperty("driver"));
			connection= DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
			}
			return connection;
		} catch (FileNotFoundException e) {
			throw new ServicesNotFoundException("SERVICES NOT AVAILABLE",e);
		} catch (ClassNotFoundException e) {
			throw new ServicesNotFoundException("SERVICES NOT AVAILABLE",e);
		} catch (IOException e) {
			throw new ServicesNotFoundException("SERVICES NOT AVAILABLE",e);
		} catch (SQLException e) {
			throw new ServicesNotFoundException("SERVICES NOT AVAILABLE",e);
		}
	}
}
