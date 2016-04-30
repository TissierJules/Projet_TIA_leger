package com.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.beans.Partie;

public interface PartieDao {
	
	int creerPartie(Partie partie) throws IllegalArgumentException, DAOException;
	
	Partie trouverID(int id_mj) throws DAOException;
	
	public void creerListeJoueur(int id_joueur, Partie partie) throws IllegalArgumentException, DAOException;
	
}
