package com.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.projet.beans.Partie;
import com.projet.forms.CreationPartieFormulaire;

import static com.projet.dao.DAOUtilitaire.*;

public class PartieDaoImpl implements PartieDao{
	private DAOFactory daoFactory;
	private static final String SQL_INSERT_PARTIE = "INSERT INTO Partie(titre, id_mj, chemin_carte, chemin_message) VALUES (?, ?, ?, ?)";
	private static final String SQL_SELECT_PAR_IDMJ = "SELECT * FROM Joueur, Partie WHERE id_joueur = ?";
	private static final String SQL_INSERT_LISTE_JOUEUR = "INSERT INTO Liste_Joueur(id_joueur, id_partie) VALUES (?,?)";
	PartieDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Partie trouverID(int id_mj) throws DAOException {
        return trouverID( SQL_SELECT_PAR_IDMJ, id_mj);
    }
	
	public int creerPartie(Partie partie) throws IllegalArgumentException, DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet valeursAutoGenerees = null;
		
		try{
			connexion = daoFactory.getConnection();
			int id_carte = CreationPartieFormulaire.id_carte;
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT_PARTIE, true, partie.getTitre(), partie.getIdMj(), partie.getCheminCarte(), partie.getCheminMessage());
			int statut = preparedStatement.executeUpdate();
			if(statut == 0){
				throw new DAOException("Echec lors de la création de la partie, aucune ligne ajoutée");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if(valeursAutoGenerees.next()){
				partie.setIdPartie(valeursAutoGenerees.getInt(1));
			}
			else{
				throw new DAOException("Echec de la création de la partie en base aucun ID auto-généré retourné");
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
		return partie.getIdPartie();
	}
	
	public void creerListeJoueur(int id_joueur, Partie partie) throws IllegalArgumentException, DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		int id_partie;

		try{
			connexion = daoFactory.getConnection();
			id_partie = partie.getIdPartie();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT_LISTE_JOUEUR, true, id_joueur, id_partie );
			int statut = preparedStatement.executeUpdate();
			if(statut == 0){
				throw new DAOException("Echec lors de la création de la partie, aucune ligne ajoutée");
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	
 	private Partie trouverID( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Partie partie = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				partie = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return partie;
	}

	private Partie trouverIdPartie( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Partie partie = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				partie = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return partie;
	}
	
	private static Partie map(ResultSet resultSet) throws SQLException{
		Partie partie = new Partie();

		partie.setIdPartie(resultSet.getInt("id_partie"));
		partie.setTitre(resultSet.getString("titre"));
		partie.setIdMj(resultSet.getInt("id_mj"));
		partie.setCheminMessage(resultSet.getString("chemin_message"));
		
		return partie;
	}
	

	
}
