package com.projet.beans;

public class Joueur {

	private int id_joueur;
	private String login;
	private String mdp;
	private String nom;
	private String prenom;
	private String mail;
	
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getMail(){
		return mail;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	public String getLogin(){
		return login;
	}
	
	public void setID(int id_joueur){
		this.id_joueur = id_joueur;
	}
	public int getID(){
		return id_joueur;
	}
	
	public void setMdp(String mdp){
		this.mdp = mdp;
	}
	public String getMdp(){
		return mdp;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getNom(){
		return nom;
	}
	
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public String getPrenom(){
		return prenom;
	}
}
