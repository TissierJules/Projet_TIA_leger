<%@page import="com.projet.parser.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>affichage</title>
<style type="text/css">
#affichage {
	text-align: left;
	margin-top: 0px;
	word-wrap: break-word;
	width: 290px;
	height: 380px;
	overflow: auto;
}
</style>
</head>
<body>
	<div id="affichage">
		<% String flux = Message.tchat; %> 
		<%= flux %>
	</div>
</body>
</html>