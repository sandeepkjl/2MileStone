package com.wipro.bank.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.bank.bean.Account;
import com.wipro.bank.bean.Customer;
import com.wipro.bank.dao.AccountDao;
import com.wipro.bank.service.AccountService;

/**
 * Servlet implementation class NewCustomerServlet
 */
@WebServlet(description = "servletforaddingCustomer", urlPatterns = { "/NewCustomerServlet" })
public class NewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accountId=Integer.parseInt(request.getParameter("accountID"));
		double balance = Double.parseDouble(request.getParameter("balance"));
		int customerId = Integer.parseInt(request.getParameter("customerID"));
		Customer customer = new Customer(customerId,
				request.getParameter("customerName"));
		
		Account account =new Account(accountId, customer,balance);
		
		accountService=new AccountService();
		String result = accountService.addAccount(account);
		
		RequestDispatcher requestDispatcher=null;
		if(result.equalsIgnoreCase("success"))
		{
			requestDispatcher= request.getRequestDispatcher("GeneralResponse.jsp");
			request.setAttribute("accountId", account.getAccountID());
			requestDispatcher.forward(request, response);
		}

		
		
		
	}

}
