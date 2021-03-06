package com.projet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DAOFactory {
	private static final String FICHIER_PROPERTIES = "/com/projet/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_JOUEUR = "nomjoueur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";
	
	private String url;
	private String username;
	private String password;
	
	DAOFactory(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomJoueur;
		String motDePasse;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);
		
		if(fichierProperties ==  null){
			throw new DAOConfigurationException("Le fichier properties "+ FICHIER_PROPERTIES + " est introuvable");
		}
		try{
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomJoueur = properties.getProperty(PROPERTY_NOM_JOUEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch ( FileNotFoundException e ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e );
		} catch (IOException e){
			throw new DAOConfigurationException("Impossible de charger le fichier properties : "+FICHIER_PROPERTIES, e);
		}
		
		try{
			Class.forName(driver);
		} catch (ClassNotFoundException e){
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath", e);
		}
		
		DAOFactory instance = new DAOFactory(url, nomJoueur, motDePasse);
		return instance;
	}
	
	Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	public JoueurDao getJoueurDao(){
		return new JoueurDaoImpl(this);
	}
	
	public PartieDao getPartieDao(){
		return new PartieDaoImpl(this);
	}
	
	
}








