<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Give Your Username Please</title>

</head>
<body>
	
		<form action="Otp" method="get">
		Username <input type="text" name="username"><br> <br>
		<input type="submit" value="Submit">
	</form>

				<td>${message}</td>
			</tr>
		</table>
	
</body>
</html>