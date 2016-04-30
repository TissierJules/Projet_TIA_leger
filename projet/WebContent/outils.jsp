<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
<!--
sfHover = function() {
    var sfEls = document.getElementById("menu").getElementsByTagName("LI");
    for (var i=0; i<sfEls.length; i++) {
            sfEls[i].onmouseover=function() {
                    this.className+=" sfhover";
            }
            sfEls[i].onmouseout=function() {
                    this.className=this.className.replace(new RegExp(" sfhover\\b"), "");
            }
    }
}
if (window.attachEvent) window.attachEvent("onload", sfHover);
-->
</script>

<style type="text/css">
#menu a:hover {/* Lorsque la souris passe sur un des liens */    
        color: #fff; 
        background: #000; 
}
td {
	border: 1px solid black;
	border-width:medium;
	text-align: center;
	width: 30px;
	height: 25px;
}
#menu, #menu ul /* Liste */ {
	padding: 0; /* pas de marge intérieure */
	margin: 0; /* ni extérieure */
	list-style: none; /* on supprime le style par défaut de la liste */
	line-height: 21px; /* on définit une hauteur pour chaque élément */
}
#menu /* Ensemble du menu */ {
	font-family: Arial; /* on utilise Arial, c'est plus beau ^^ */
}
#menu a /* Contenu des listes */ {
	display: block;	/* on change le type d'élément, les liens deviennent des balises de type block */
	padding: 0; /* aucune marge intérieure */
	color: #000; /* couleur du texte */
	text-decoration: none;	/* on supprime le style par défaut des liens (la plupart du temps = souligné) */
}
/* IE ne reconnaissant pas le sélecteur ">" */
html>body #menu li {
	border-right: 1px solid transparent;
	/* on met une bordure transparente à droite de chaque élément */
}
#menu li ul /* Sous-listes */ {
	font-weight: bold; /* on met le texte en gras */
	background : #DDD;
	position: absolute; /* Position absolue */
	left: -999em; /* Hop, on envoie loin du champ de vision */
}
#menu li ul li /* Éléments de sous-listes */ {
	/* pour IE qui ne reconnaît pas "transparent" (comme précédemment) */
	border-top: 1px solid #fff;
	/* on met une bordure blanche en haut de chaque élément d'une sous liste */
}
/* IE ne reconnaissant pas le sélecteur ">" */
html>body #menu li ul li {
	border-top: 1px solid transparent;
	/* on met une bordure transparente en haut de chaque élément */
}
#menu li:hover ul, #menu li li:hover ul{
        left: auto; /* Repositionnement normal */
        min-height: 0; /* Corrige un bug sous IE */
}

#bleu {

}
</style>

<title>Outils</title>
</head>

<body>
	<table>
		<tr>
			<td colspan="2">inviter</td>
			<td colspan="2">
				<ul id="menu">
					<li><a href="#">liste</a>
						<ul>
							<li><a href="#">perso1</a></li>
							<li><a href="#">perso2</a></li>
							<li><a href="#">perso3</a></li>
							<li><a href="#">perso4</a></li>
							<li><a href="#">perso5</a></li>
						</ul></li>
				</ul>
			</td>
			<td colspan="2">charger</td>
		</tr>
		<tr>
			<td><a id="bleu" onclick="bleu()"><img src="./sources_image/bleu.png" alt="bleu"/></a></td>
			<td><a id="beige" onclick="beige()"><img src="./sources_image/beige.png" alt="beige"/></a></td>
			<td><a id="marron" onclick="marron()"><img src="./sources_image/marron.png" alt="marron"/></a></td>
			<td><a id="orange" onclick="orange()"><img src="./sources_image/orange.png" alt="orange"/></a></td>
			<td><a id="rouge" onclick="rouge()"><img src="./sources_image/rouge.png" alt="rouge"/></a></td>
			<td><a id="pnj" onclick="pnj()"><img src="./sources_image/pnj.png" alt="pnj"/></a></td>
		</tr>
		<tr>
			<td><a id="fsn" onclick="fsn()"><img src="./sources_image/fsn.png" alt="fsn"/></a></td>
			<td><a id="fsone" onclick="fsone()"><img src="./sources_image/fsone.png" alt="fsone"/></a></td>
			<td><a id="foe" onclick="foe()"><img src="./sources_image/foe.png" alt="foe"/></a></td>
			<td><a id="fnose" onclick="fnose()"><img src="./sources_image/fnose.png" alt="fnose"/></a></td>
			<td><a id="fns" onclick="fns()"><img src="./sources_image/fns.png" alt="fns"/></a></td>
			<td><a id="fneso" onclick="fneso()"><img src="./sources_image/fneso.png" alt="fneso"/></a></td>
		</tr>
		<tr>
			<td><a id="feo" onclick="feo()"><img src="./sources_image/feo.png" alt="feo"/></a></td>
			<td><a id="fseno" onclick="fseno()"><img src="./sources_image/fseno.png" alt="fsone"/></a></td>
			<td><a id="masque2" onclick="mask()"><img src="./sources_image/masque2.png" alt="masque2" height="20px" width="20px"/></a></td>
			<td></td>
			<td><a id="mv" onclick="mv()">MV</a></td>
			<td><a id="rm" onclick="rm()">RM</a></td>
		</tr>
	</table>
</body>
<script src="outils.js"></script>
</html>