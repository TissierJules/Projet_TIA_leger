package com.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.lang.model.element.ExecutableElement;

import com.projet.beans.Joueur;
import static com.projet.dao.DAOUtilitaire.*;

public class JoueurDaoImpl implements JoueurDao{
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_MAIL = "SELECT * FROM Joueur WHERE mail = ?";
	private static final String SQL_SELECT_PAR_MDP  = "SELECT * FROM Joueur WHERE mdp = ? AND login = ?";
	private static final String SQL_SELECT_PAR_LOGIN = "SELECT * FROM Joueur WHERE login = ?";
	private static final String SQL_SELECT_PARTIE_JOUEUR = "SELECT Partie.id_partie, titre FROM Partie, Liste_Joueur WHERE Liste_Joueur.id_joueur = ? AND Liste_Joueur.id_partie = Partie.id_partie";
	private static final String SQL_INSERT ="INSERT INTO Joueur(login, nom, prenom, mdp, mail) VALUES (?, ?, ?, ?, ?)";
	
	JoueurDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
		public Joueur trouver( String mail ) throws DAOException {
	        return trouver( SQL_SELECT_PAR_MAIL, mail );
	    }
	
	@Override
	public Joueur trouverMdp( String mdp, String login ) throws DAOException {
        return trouverMdp( SQL_SELECT_PAR_MDP, mdp, login );
    }
	
	@Override
	public Joueur trouverLogin( String login) throws DAOException {
        return trouverLogin( SQL_SELECT_PAR_LOGIN, login);
    }
	
	@Override
	public ResultSet trouverPartieJoueur( int id_joueur) throws DAOException, SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;	
		connexion = daoFactory.getConnection();
		preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_PARTIE_JOUEUR, true, id_joueur);
		return preparedStatement.executeQuery();
	}
	
	
	@Override
	public void creer(Joueur joueur) throws IllegalArgumentException, DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, joueur.getLogin(), joueur.getNom(), joueur.getPrenom(), joueur.getMdp(), joueur.getMail());
			int statut = preparedStatement.executeUpdate();
			if(statut == 0){
				throw new DAOException("Echec lors de la création de l'utilisateur, aucune ligne ajoutée");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if(valeursAutoGenerees.next()){
				joueur.setID(valeursAutoGenerees.getInt(1));
			}
			else{
				throw new DAOException("Echec de la création du joueur en base aucun ID auto-généré retourné");
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}
	
	
	private Joueur trouver( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Joueur joueur = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				joueur = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return joueur;
	}
	
	private Joueur trouverMdp( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Joueur joueur = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				joueur = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return joueur;
	}
	
	private Joueur trouverLogin( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Joueur joueur = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				joueur = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return joueur;
	}
	
	/*private Joueur trouverPartieJoueur( String sql, Object... objets ) throws DAOException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Joueur joueur = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				joueur = map(resultSet);
			}
		} catch(SQLException e){
			throw new DAOException(e);
		} finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return joueur;
	}*/
	
	private static Joueur map(ResultSet resultSet) throws SQLException{
		Joueur joueur = new Joueur();

		joueur.setID(resultSet.getInt("id_joueur"));
		joueur.setLogin(resultSet.getString("login"));
		joueur.setMail(resultSet.getString("mail"));
		joueur.setMdp(resultSet.getString("mdp"));
		joueur.setNom(resultSet.getString("nom"));
		joueur.setPrenom(resultSet.getString("prenom"));
		return joueur;
	}
	
	
	
	
}
