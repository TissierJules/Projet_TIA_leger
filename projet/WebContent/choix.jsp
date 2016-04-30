<%@page import="com.projet.dao.DAOFactory"%>
<%@page import="com.projet.servlets.ChoixComboBox"%>
<%@page import="com.projet.beans.Joueur" %>
<%@page import="com.projet.beans.Partie" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Choix Partie</title>
		<link type="text/css" rel="stylesheet" href="form.css"/>


		<script type="text/javascript" >
			function Redirection(){
				 window.location.replace("choix.java");
				 return false;
			}	
			
			function masquer_div(id)
			{
			  if (document.getElementById(id).style.display == 'none')
			  {
			       document.getElementById(id).style.display = 'block';
			  }
			  else
			  {
			       document.getElementById(id).style.display = 'none';
			  }
			}
			
			function verification()
			{
				var champ_obligatoire = 'partie';
				var champ_plein = true;
			
				valeur = document.getElementById(champ_obligatoire).value;
				if( (valeur.length == 0) || (valeur == "") || (valeur == "NULL") ){
					champ_plein = false;
				}
				
				if (champ_plein){
					document.getElementById('partie').disabled = false;
			 	}
			else{
				document.getElementById('partie').disabled = true;
			}
		}
			
			function RedirectionJavascript(){
				var xhr = new XMLHttpRequest();
				var idc = document.getElementById("id_carte");
				var idj = document.getElementsByName("CHAMP_ID_MJ");
				
				var id_carte = encodeURIComponent(idc);
				var id_joueur = encodeURIComponent(idj);
				
				xhr.open('GET', 'principale.jsp?id_carte='+id_carte+'id_joueur='+id_joueur);
				xhr.send(null);
			}
			
		</script>
	</head>
	<body>
		<form method="post" action="creerPartie">
			<label for="new"><a class="text">Créer une partie : </a></label>
			<input hidden type="text" class="partie1" id="1" name="CHAMP_ID_MJ" value="<%= ((Joueur)session.getAttribute("sessionJoueur")).getID() %>"/>
			<input type="text" class="partie1" id="titre" name="titre" value="<c:out value="${partie.titre}"/>" size="20" maxlength="60" onchange="verification();" />
			<span class="erreur">${form.erreurs['titre']}</span>
			<div id="a_masquer">
				<br/>
				<input type="radio" id="id_carte" name="id_carte" value="1"> La Taverne Noir <br/>
				<input type="radio" id="id_carte" name="id_carte" value="2"> Le Donjon Maudit <br/>
			</div>
			
			<input type="submit" id="partie" name="partie" value="Créer une Partie" size="20" maxlength="60" />
			<br/>
			<br/>
			
		</form>
			<br/>
			<br/>
			<p> Rejoindre une partie en cours </p>
			<form method="post" action="principale.jsp">
			<select name="thelist" class="select">
				<%
				new ChoixComboBox(((Joueur)session.getAttribute("sessionJoueur")).getID(), ((DAOFactory) getServletContext().getAttribute("daofactory")).getJoueurDao());
				String flux = ChoixComboBox.jsp;%>
				<%= flux %>
			</select>
			<input type="submit" value="Choix" class="sansLabel1"/>
			<br/> 
			
		</form>
</body>
</html>