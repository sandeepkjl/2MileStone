package com.wipro.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.bank.bean.Account;
import com.wipro.bank.bean.Customer;
import com.wipro.bank.service.AccountService;

/**
 * Servlet implementation class FundTransferServlet
 */
@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService = null;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fromAccountId=Integer.parseInt(request.getParameter("fromAccountID"));
		int toAccountId=Integer.parseInt(request.getParameter("toAccountID"));
		double fundForTransfer = Double.parseDouble(request.getParameter("balance"));
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		accountService=new AccountService();
		
		String tranferSatus = accountService.transferFunds(fromAccountId, toAccountId, fundForTransfer);
		
		pw.println(tranferSatus);
		

	}

}
