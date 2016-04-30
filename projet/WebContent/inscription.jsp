<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="form.css"/>
		<title>Formulaire d'inscription</title>
		
		<script type="text/javascript" >
			function Redirection(){
				 window.location.replace("connexion.jsp");
				  return false;
				}
		</script>
	</head>
	<body> 
		<form  id="form1" method="post" action="inscription">
			<fieldset>
				<legend>Inscription</legend>
				<p> Vous pouvez vous inscrire via ce formulaire.</p>
				
				
				<label for="mail">Adresse Email <span class="requis">*</span></label>
				<input type="email" id="mail" name="mail" value="<c:out value="${joueur.mail}"/>" size="20" maxlength="60" />
				<span class="erreur">${form.erreurs['mail']}</span>
				<br/>
				
				<label for="login">Login <span class="requis">*</span></label>
				<input type="text" id="login" name="login" value="<c:out value="${joueur.login}"/>" size="20" maxlength="20" />
				<span class="erreur">${form.erreurs['login']}</span>
				<br/>
				
				<label for="mdp">Mot de Passe <span class="requis">*</span></label>
				<input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20" />
				<span class="erreur">${form.erreurs['mdp']}</span>
				<br/>
				
				<label for="mdp2">Mot de Passe Confirmation <span class="requis">*</span></label>
				<input type="password" id="mdp2" name="mdp2" value="" size="20" maxlength="20" />
				<span class="erreur">${form.erreurs['mdp2']}</span>
				<br/>
				
				<label for="nom">Nom <span class="requis">*</span></label>
				<input type="text" id="nom" name="nom" value="<c:out value="${joueur.nom}"/>" size="20" maxlength="20" />
				<span class="erreur">${form.erreurs['nom']}</span>
				<br/>
				
				<label for="prenom">Prénom <span class="requis">*</span></label>
				<input type="text" id="prenom" name="prenom" value="<c:out value="${joueur.prenom}"/>" size="20" maxlength="20" />
				<span class="erreur">${form.erreurs['prenom']}</span>
				<br/>
				
				
				
				<input type="submit" value="Inscription" class="sansLabel" onsubmit="return Redirection()"/>
				<br/>
				
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>	
			</fieldset>
		</form>
	</body>
</html>