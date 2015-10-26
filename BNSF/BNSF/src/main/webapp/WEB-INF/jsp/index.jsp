<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/employee/addEmployeeToRoster">Add Employee to Roster</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/roster/createRoster">Create Roster</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/roster/getRoster">Get Roster</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/station/createStation">Create Station</a>
		</li>
	</ul>
</body>
</html>