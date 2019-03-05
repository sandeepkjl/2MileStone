package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wipro.bank.bean.Account;
import com.wipro.bank.bean.Customer;
import com.wipro.et.util.DBUtil;

public class AccountDao {
	
	public  PreparedStatement pstmt =null;

	public String saveAccount(Account account)
	{
		try {
			
			Connection con = DBUtil.getDBConnection();
			
		    pstmt= con.prepareStatement("insert into CustomerProfile values(?,?)");
			
			//saving customer Record;
		    pstmt.setInt(1,account.getCustomer().getCustomerID());
		    pstmt.setString(2, account.getCustomer().getName());
			
		    pstmt.execute();
			
			pstmt =
					con.prepareStatement("insert into account values(account_sequence.NEXTVAL,?,?,?)");
			
			pstmt.setInt(1, account.getAccountID());
			pstmt.setInt(2, account.getCustomer().getCustomerID());
			pstmt.setDouble(3, account.getBalance());
			
		    pstmt.executeUpdate();
		    
		    pstmt.close();
		    con.close();
			return "Success";
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public List<Account> findAll()
	{
		List<Account> accounts = new ArrayList<Account>();
		Connection con = DBUtil.getDBConnection();
		try {
			pstmt= con.prepareStatement("Select * from Account");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
		    	int accountId = rs.getInt(2);		    	
		    	int customerId = rs.getInt(3);
		    	String custName=null;
		    	double balance = rs.getDouble(4);
		    	
		    	//fetching Customer Information
		    	PreparedStatement pstmtCust= con.prepareStatement("Select * from CustomerProfile where Id=?");
		    	pstmtCust.setInt(1, customerId);
		    	
		    	ResultSet rsCust = pstmtCust.executeQuery();
		    	while(rsCust.next())
		    	{
		    		custName = rsCust.getString(2);
		    	}
		    	Customer customer= new Customer(customerId, custName);
		    	Account acct = new Account(accountId, customer, balance);
		    	
		    	accounts.add(acct);    	
		    	
		    }
			
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return accounts;
		
		}
		
	}
	
	public Account findAccount(int acctId)
	{
		Connection con = DBUtil.getDBConnection();
		
		
	    try {
			pstmt= con.prepareStatement("Select * from Account where Accountid=?");
		
			//fetching AccountDetails Record;
		    pstmt.setInt(1, acctId);
		    ResultSet rs = pstmt.executeQuery();
		    
		    Account account = new Account();
		    Customer customer = new Customer();
		    while(rs.next()){
		    	int accountId = rs.getInt(2);
		    	account.setAccountID(accountId);		    	
		    	int customerId = rs.getInt(3);
		    	customer.setCustomerID(customerId);
		    	double balance = rs.getDouble(4);
		    	
		    	account.setBalance(balance);
		    	
		    }
		    
		    pstmt= con.prepareStatement("Select * from CustomerProfile where Id=?");
		    pstmt.setInt(1, customer.getCustomerID());
		    
		    ResultSet rsCustomer= pstmt.executeQuery();
		    while(rsCustomer.next())
		    {
		    	customer.setName(rsCustomer.getString(2));
		    }
		    
		    account.setCustomer(customer);
		    
		    pstmt.close();
		    con.close();
		    
		    return account;
		    	    
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public String transferFunds(int fromAccountId, int toAccountId, double fundForTransfer)
	{
		Connection con = DBUtil.getDBConnection();
		try {
			pstmt= con.prepareStatement("update account set balance = balance-? where AccountId=?");
			pstmt.setDouble(1, fundForTransfer);
			pstmt.setInt(2, fromAccountId);
			pstmt.executeUpdate();
			
			pstmt= con.prepareStatement("update account set balance = balance+? where AccountId=?");
			pstmt.setDouble(1, fundForTransfer);
			pstmt.setInt(2, toAccountId);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			return "Success";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
	}
	
	private Connection getDBConnection()
	{
		String dbUrl= "jdbc:oracle:thin:@localhost:1521:xe";
		String dbname="devops";
		String dbpwd="accounttracker";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(dbUrl, dbname,dbpwd);
			
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
