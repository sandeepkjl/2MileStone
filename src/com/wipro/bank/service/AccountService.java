package com.wipro.bank.service;


import java.util.List;

import com.wipro.bank.bean.Account;
import com.wipro.bank.bean.Customer;
import com.wipro.bank.dao.AccountDao;

public class AccountService {
	
	AccountDao accountDao=new AccountDao();
	
	
	
	public String addAccount(Account acc)
	{
		String addStatus=accountDao.saveAccount(acc);
		if(addStatus!=null)
		{
			return "success";
		}
		else{
			return "failed";
		}
	}
	
	public String transferFunds(int from,int to,double amount)
	{
		Account fromAccountDetails =getBalanceOf(from);
		Account toAccountDetails= getBalanceOf(to);
		if(fromAccountDetails==null)
		{
			return "From Account Does not Exist";
		}
		if(toAccountDetails==null)
		{
			return "To Account Does not Exist";
		}
		
		if(fromAccountDetails.getBalance()<amount)
		{
			return "insufficeint balance";
		}
		 String status = accountDao.transferFunds(from,
				 to,amount);
		 
		 if(status.equals("Success"))
			 return "Funds Successfully Transferred";
		 else
			 return "Funds Transfer Failed";
			 
	}
	
	public List<Account> findAllAccountsDeatils()
	{
		List<Account> accounts = accountDao.findAll();
		return accounts;
	}
	
	public Account getBalanceOf(int accountNumber)
	{
		Account account = getAccountDetailsById(accountNumber);
		return account;
	}
	
	public Account getAccountDetailsById(int accountNumber){
		Account account = accountDao.findAccount(accountNumber);
		return account;
	}
}
