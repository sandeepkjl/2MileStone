package com.wipro.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.bank.bean.Account;
import com.wipro.bank.service.AccountService;

/**
 * Servlet implementation class GetAccountBalanceServlet
 */
@WebServlet("/GetAccountBalanceServlet")
public class GetAccountBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService = null;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountBalanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int accountId=Integer.parseInt(request.getParameter("accountID"));
		
		accountService=new AccountService();
		
		Account account = accountService.getBalanceOf(accountId);
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.println("Account Deatils are");
		
		pw.println(account);		
		
	}
}
