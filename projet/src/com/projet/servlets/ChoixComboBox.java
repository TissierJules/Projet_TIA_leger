package com.projet.servlets;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.dao.*;

public class ChoixComboBox {
	private static final long serialVersionUID = 102831973239L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	private JoueurDao joueurDao;
	public static String jsp="";

	public ChoixComboBox(int id_joueur, JoueurDao DAO){
		System.out.println(id_joueur);
		joueurDao= DAO;
		try {
			ResultSet rs = joueurDao.trouverPartieJoueur(id_joueur);
			while(rs.next())
			{
				jsp+="<option>"+rs.getString(2)+"</option>";
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
