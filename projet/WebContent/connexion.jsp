<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Connexion</title>
		<link type="text/css" rel="stylesheet" href="form.css"/>
	</head>
	<body>
		<form method="post" action="connexion">
			<fieldset>
				<legend>Connexion</legend>
				<p>Vous pouvez vous connectez via ce formulaire</p>
				
				<label for="login">Login<span class="requis">*</span></label>
				<input type="text" id="login" name="login" value="<c:out value="${joueur.login}"/>" size="20" maxlength="60"/>
				<span class="erreur">${form.erreurs['login']}</span>
				<br/>
				
				<label for="mdp">Mot de Passe<span class="requis">*</span></label>
				<input type="password" id="mdp" name="mdp" value="<c:out value="${joueur.mdp}"/>" size="20" maxlength="60"/>
				<span class="erreur">${form.erreurs['mdp']}</span>
				<br/>
				
				<input type="submit" value="Connexion" class="sansLabel"/>
				<br/>
				
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			
				<c:if test="${!empty sessionScope.sessionJoueur}">
					<input type="button" value="Continuer" class="sansLabel" onClick="javascript:document.location.href='choix.jsp'" />
				</c:if>
			</fieldset>
		</form>
	</body>
</html>