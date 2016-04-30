package com.projet.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.beans.Joueur;
import com.projet.dao.*;

public final class ConnexionFormulaire {
    private static final String CHAMP_LOGIN = "login";
    private static final String CHAMP_MDP   = "mdp";
    private static final String CHAMP_MAIL = "mail";
    private static final String CHAMP_ID = "id_joueur";
	private JoueurDao joueurDao;
	private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public ConnexionFormulaire (JoueurDao joueurDao){
		this.joueurDao = joueurDao;
	}
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    
   
    public Joueur connecterJoueur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
   
        String login = getValeurChamp(request, CHAMP_LOGIN);
        String mdp = getValeurChamp( request, CHAMP_MDP );
        String mail = getValeurChamp(request, CHAMP_MAIL);
        Joueur joueur = new Joueur();
	
        try{
        	traiterLogin(login, joueur);
        	traiterMdp(mdp, login, joueur);
        	if(erreurs.isEmpty()){
				resultat = "Succès de l'inscription";
        	}
			else{
				resultat = "Echec de l'inscription";
			}
        }catch (DAOException e){
				resultat = "Echec de l'inscription : une erreur imprévue est survenue merci de réessayer dans un petit instant";
				e.printStackTrace();
		}
		return joueurDao.trouverLogin(login);
       }
    
    	private void traiterLogin(String login, Joueur joueur){
    		try{
    			validationLogin(login);
    		} catch (FormValidationException e){
    			setErreur(CHAMP_LOGIN, e.getMessage());
    		}
    		joueur.setLogin(login);
    	} 
    	
    	private void traiterMdp(String mdp, String login, Joueur joueur){
    		try{
    			validationMdp(mdp, login);
    		} catch(FormValidationException e){
    			setErreur(CHAMP_MDP, e.getMessage());
    		}
    		joueur.setMdp(mdp);
    	}
	      
		private void validationLogin(String login) throws FormValidationException{
			if(login != null){
				if(login.length() < 3){
					throw new FormValidationException("Merci de rentrer un login de longueur > 3.");
				}
				else if(joueurDao.trouverLogin(login) == null){
					throw new FormValidationException("");
				}
			}
			else{
				throw new FormValidationException("Merci de saisir une nouvelle adresse mail.");
			}
		}
    /**
     * Valide le mot de passe saisi.
     */
    private void validationMdp( String mdp, String login ) throws FormValidationException {
        if ( mdp == null){
                throw new FormValidationException( "Le mot de passe est nul !" );
        }
        if(joueurDao.trouverMdp(mdp, login) == null){
        	throw new FormValidationException("Pas bon mot de passe");
        }
        	
    }
    

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}