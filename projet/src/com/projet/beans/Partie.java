package com.projet.beans;

public class Partie {
	private int id_partie;
	private String titre;
	private int id_mj;
	private String chemin_message;
	private String chemin_carte;
	
	public void setIdPartie(int id_partie){
		this.id_partie = id_partie;
	}
	
	public int getIdPartie(){
		return id_partie;
	}
	
	public void setCheminCarte(String chemin_carte){
		this.chemin_carte = chemin_carte;
	}
	
	public String getCheminCarte(){
		return chemin_carte;
	}
	
	public void setTitre(String titre){
		this.titre = titre;
	}
	
	public String getTitre(){
		return titre;
	}
	
	public void setIdMj(int id_mj){
		this.id_mj = id_mj;
	}
	
	public int getIdMj(){
		return id_mj;
	}
	
	public void setCheminMessage(String chemin_message){
		this.chemin_message = chemin_message;
	}
	
	public String getCheminMessage(){
		return chemin_message;
	}
}
