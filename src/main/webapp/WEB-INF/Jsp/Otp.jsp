<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OTP Generation</title>
</head>
<body bgcolor="magenta"><center>
	<form action="Password" method="post">
		OTP: <input type="text" name="otp"><br> <br>
		<input type="submit" value="Submit">
	</form>
	</center>
<table><td>${message}</td></table>
</body>
</html>