package com.projet.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.projet.dao.*;
import com.projet.beans.Joueur;


public final class InscriptionFormulaire {
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_MDP = "mdp";
	private static final String CHAMP_MDP2 = "mdp2"; 
	private static final String CHAMP_NOM = "nom"; 
	private static final String CHAMP_PRENOM = "prenom"; 
	private static final String CHAMP_MAIL = "mail";
	private JoueurDao joueurDao;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public InscriptionFormulaire (JoueurDao joueurDao){
		this.joueurDao = joueurDao;
	}
	
	public String getResultat(){
		return resultat;
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	
	public Joueur inscrireJoueur(HttpServletRequest request){
		String mdp = getValeurChamp(request, CHAMP_MDP);
		String mdp2 = getValeurChamp(request, CHAMP_MDP2);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String mail = getValeurChamp(request, CHAMP_MAIL);
		String login = getValeurChamp(request, CHAMP_LOGIN);

		Joueur joueur = new Joueur();
		
		try{
			traiterMail(mail, joueur);
			traiterLogin(login, joueur);
			traiterMdp(mdp, mdp2, joueur);
			traiterNom(nom, joueur);
			traiterPrenom(prenom, joueur);
			
			if(erreurs.isEmpty()){
				joueurDao.creer(joueur);
				resultat = "Succès de l'inscription";
			}
			else{
				resultat = "Echec de l'inscription";
			}
		} catch (DAOException e){
			resultat = "Echec de l'inscription : une erreur imprévue est survenue merci de réessayer dans un petit instant";
			e.printStackTrace();
		}
		return joueur;
	}
	
		private void traiterMail(String mail, Joueur joueur){
			try{
				validationMail(mail);
			} catch (FormValidationException e){
				setErreur(CHAMP_MAIL, e.getMessage());
			}
			joueur.setMail(mail);
		}
		
		private void traiterLogin(String login, Joueur joueur){
			try{
				validationLogin(login);
			} catch (FormValidationException e){
				setErreur(CHAMP_LOGIN, e.getMessage());
			}
			joueur.setLogin(login);
		}
		
		private void traiterMdp(String mdp, String mdp2, Joueur joueur){
			try{
				validationMdp(mdp, mdp2);
			} catch (FormValidationException e){
				setErreur(CHAMP_MDP, e.getMessage());
				setErreur(CHAMP_MDP2, null);
			}
			joueur.setMdp(mdp);
		}
		
		private void traiterNom(String nom, Joueur joueur){
			try{
				validationNom(nom);
			} catch (Exception e){
				setErreur(CHAMP_NOM, e.getMessage());
			}
			joueur.setNom(nom);
		}
		
		private void traiterPrenom(String prenom, Joueur joueur){
			try{
				validationPrenom(prenom);
			} catch (Exception e){
				setErreur(CHAMP_PRENOM, e.getMessage());
			}
			joueur.setPrenom(prenom);
		
		}	
		
		private void validationMail(String mail) throws FormValidationException{
			if(mail != null){
				if(!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
					throw new FormValidationException("Merci de saisir une adresse mail valide.");
				}
				else if(joueurDao.trouver(mail) != null){
					throw new FormValidationException("Vous êtes déjà inscrit");
				}
			}
			else{
				throw new FormValidationException("Merci de saisir une nouvelle adresse mail.");
			}
		}
		
		private void validationLogin(String login) throws FormValidationException{
			if(login != null && login.length() < 3){
				throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 3 lettres.");
			}
			/*if(joueurDao.trouverLogin(login)) != null){
				throw new FormValidationException("Login déjà utilisé");
			}*/
		}
	
		private void validationMdp(String mdp, String mdp2) throws FormValidationException{
			if(mdp != null && mdp2 != null){
				if(!mdp.equals(mdp2)){
					throw new FormValidationException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
				}
				else if(mdp.length() < 3){
					throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caractères.");
				}
			}
			else{
				throw new FormValidationException("Merci de saisir et confirmer vos mots de passe.");
			}
		}
	
		private void validationNom(String nom) throws FormValidationException{
			if(nom != null && nom.length() < 3){
				throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 3 lettres.");
			}
		}
	
		private void validationPrenom(String prenom) throws FormValidationException{
			if(prenom != null && prenom.length() < 3){
				throw new FormValidationException("Le prenom d'utilisateur doit contenir au moins 3 lettres.");
			}
		}
	
		private void setErreur(String champ, String message){
			erreurs.put(champ, message);
		}
	
		private static String getValeurChamp(HttpServletRequest request, String nomChamp){
			String valeur = request.getParameter(nomChamp);
			if(valeur == null || valeur.trim().length() == 0){
				return null;
			}
			else{
				return valeur;
			}
		}

}


