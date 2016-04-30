<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
		<link type="text/css" rel="stylesheet" href="form.css"/>

</head>
<body>
<h3>Jeu de Rôle</h3>
<div id="menu">
	<p>
		<a id="bouton1" href="<c:url value="/inscription"/>">Inscrivez Vous</a>
	</p>
	<p>
		<a id="bouton2" href="<c:url value="/connexion"/>">Connexion</a>
	</p>
</div>
</body>
</html>