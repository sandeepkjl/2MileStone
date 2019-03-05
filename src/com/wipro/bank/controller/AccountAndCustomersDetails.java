package com.wipro.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.bank.bean.Account;
import com.wipro.bank.service.AccountService;

/**
 * Servlet implementation class AccountAndCustomersDetails
 */
@WebServlet("/AccountAndCustomersDetails")
public class AccountAndCustomersDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountAndCustomersDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        accountService=new AccountService();
		
		List<Account> accounts = accountService.findAllAccountsDeatils();
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.println("Accounts Deatils are");
		
		pw.println(accounts);	
	}

}
