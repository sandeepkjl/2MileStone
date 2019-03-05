<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AccountForm</title>
</head>
<body>

<h3> Enter Account Details</h3>

<form action="NewCustomerServlet" , method="post">
Enter accountID: <input type="text" name="accountID"> </br>
Enter customerID: <input type="text" name="customerID"> </br>
Enter customerName: <input type="text" name="customerName"> </br>
Enter balance: <input type="text" name="balance"> </br>

<input type="submit" value="createCustomer">

</form>

<h3>Create Account</h3>
</body>
</html>