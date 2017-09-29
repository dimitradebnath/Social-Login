<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<style type="text/css">
.error{
color:red;
}
</style>
</head>
<body>
${errors}
	<form:form id="registrationfrom" modelAttribute="registration"
		action="registrationProcess" method="post">
		<table align="center">
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" cssClass="error"></form:errors></td>
				
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password"></form:password></td>
				<td><form:errors path="password" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="Email">Email</form:label></td>
				<td><form:input path="Email"></form:input></td>
				<td><form:errors path="Email" cssClass="error"></form:errors></td>
			</tr>
	         <tr>
				<td><form:label path="firstname">FirstName</form:label></td>
				<td><form:input path="firstname"/></td>
				<td><form:errors path="firstname" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="lastname">LastName</form:label></td>
				<td><form:input path="lastname"/>
				<td><form:errors path="lastname" cssClass="error"></form:errors></td>
				</td>
			</tr>

			<tr>
				<td></td>
				<td><form:button id="register" name="register" >Register</form:button>
				</td>
			</tr>
			<tr></tr>


		</table>
	</form:form>
</body>
</html>