<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer fund Page</title>
</head>
<body>
<h3>Transfer fund</h3>

<form action="FundTransferServlet" , method="post">
Enter fromAccountID: <input type="text" name="fromAccountID"> </br>
Enter toAccountID: <input type="text" name="toAccountID"> </br>
Enter balance: <input type="text" name="balance"> </br>

<input type="submit" value="submit">
</body>
</html>