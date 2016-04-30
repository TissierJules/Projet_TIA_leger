<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>principale</title>

<style type="text/css">
tr, td {
	border: 1px solid black;
}
</style>
<script>

</script>
</head>
<body>

	<table>
		<tr>
			<td rowspan="3" id="carte">
			<% String id_carte = (String) request.getParameter("id_carte");%>
				<div id="divcarte" onclick="Marqueur(event,'divcarte')" > <jsp:include page="./carte.jsp" /></div>
			</td>
			<td id="affichage">
				<div ><jsp:include page="./affichage.jsp" /></div>
			</td>
		</tr>
		<tr>
			<td id="saisie">
				<div ><jsp:include page="./saisie.html" /></div>
			</td>
		</tr>
		<tr>
			<td id="outils">
				<div ><jsp:include page="./outils.jsp" /></div>
			</td>
		</tr>
	</table>


</body>
<script src="outils.js"></script>
</html>