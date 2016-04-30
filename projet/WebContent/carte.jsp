<%@page import="com.projet.beans.Partie"%>
<%@page import="com.projet.parser.Dessin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>carte</title>
<style type="text/css">
#carte {
	width: 80%;
	overflow: auto;
}
</style>
</head>
<body >
	<% Dessin d =new Dessin("","","","","","");
	System.out.println(((Partie)request.getAttribute("partie")).getIdPartie());
	d.afficher("carte_"+ ((Partie)request.getAttribute("partie")).getIdPartie() +".xml");
	String flux = Dessin.dessin;%> 
	<%= flux %>
</body>
</html>