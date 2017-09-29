<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>

<style type="text/css">
.error{
color:red;
}
</style>
<body bgcolor="cyan"><center>
<h1>Login Page</h1>
	<form:form modelAttribute="login" method="post" action="loginprocess">
		<table align="center">
			<tr>
				<td>Username</td>
				<td><form:input path="username"></form:input>
				<td><form:errors path="username" cssClass="error"></form:errors>
				</td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password"></form:password>
				</td><form:errors path="password" cssClass="error"></form:errors>
			</tr>
			<tr>
				<td></td>
				<td align="left"><form:button id="login" name="login">Login</form:button>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><a href="registration">Registration for new user</a></td>
				<br>
				<br>
				<td></td>
			</tr>
			
		</table>
	</form:form>
	            <table align="center">

                <tr>

                    <td style="font-style: italic; color: red;">${message}</td>

                </tr>
<a href="uname">Forgot Password</a>
            </table>
            <a href="loginWithGoogle">Login With Google</a>
	
</body>
</html>