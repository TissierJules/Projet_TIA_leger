package com.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.beans.Joueur;

public interface JoueurDao {

	void creer(Joueur joueur) throws DAOException;
	
	Joueur trouver(String mail) throws DAOException;

	Joueur trouverMdp(String mdp, String login) throws DAOException;

	Joueur trouverLogin(String login) throws DAOException;
	
	ResultSet trouverPartieJoueur( int id_joueur) throws DAOException, SQLException;

}
