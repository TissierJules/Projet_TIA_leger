package com.projet.forms;
import com.projet.parser.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.projet.dao.*;
import com.projet.beans.Partie;

public class CreationPartieFormulaire {
	private static final String CHAMP_TITRE = "titre";	
	private static int CHAMP_ID_MJ;
	public static int id_carte;
	private static int id_partie;
	private PartieDao partieDao;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public CreationPartieFormulaire(PartieDao partieDao){
		this.partieDao = partieDao;
	}
	
	public String getResultat(){
		return resultat;
	}
	
	public Map<String, String> getErreurs(){
		return erreurs;
	}
	
	public Partie inscrirePartie(HttpServletRequest request){
		String titre = getValeurChamp(request, CHAMP_TITRE);
		int id_mj = Integer.parseInt(getValeurChamp(request, "CHAMP_ID_MJ"));
		Partie partie = new Partie();
		
		try{ 
			traiterTitre(titre, partie);
			//traiterIDMJ(id_mj, partie);
			if(erreurs.isEmpty()){
				partie.setIdMj(id_mj);
				id_partie = partieDao.creerPartie(partie);
		 
				id_carte = Integer.parseInt(request.getParameter("id_carte"));
				Dessin.creerFichier(id_partie, id_carte);
				try {
					Message.creerFichier(id_partie);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				partieDao.creerListeJoueur(id_mj , partie);
				resultat = "Succès de la création";
			}
			else{
				resultat = "Echec de la création";
			}
		} catch (DAOException e){
			resultat = "Echec de la création : une erreur imprévue est survenue merci de réessayer dans un petit instant";
			e.printStackTrace();
		}
		return partie;
	}
	
	private void traiterTitre(String titre, Partie partie){
		try{
			validationTitre(titre);
		} catch (FormValidationException e){
			setErreur(CHAMP_TITRE, e.getMessage());
		}
		partie.setTitre(titre);
	}
	
	
	private void validationTitre(String titre) throws FormValidationException{
		if(titre == null){
			throw new FormValidationException("Merci de saisir un texte valide");
		}
	}
	
	private void validationIDMJ(int id_mj) throws FormValidationException{
		if(partieDao.trouverID(id_mj) == null){
			throw new FormValidationException("Une erreur de votre id mj est survenue");
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

