package com.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projet.beans.Joueur;
import com.projet.dao.DAOFactory;
import com.projet.dao.JoueurDao;
import com.projet.forms.ConnexionFormulaire;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "joueur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionJoueur";
	public static final String VUE = "/connexion.jsp";
	private JoueurDao joueurDao;

	
	public void init() throws ServletException{
		this.joueurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getJoueurDao();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnexionFormulaire form = new ConnexionFormulaire(joueurDao);
		
		Joueur joueur = form.connecterJoueur(request);
		
		HttpSession session = request.getSession();
		
		if(form.getErreurs().isEmpty()){
			session.setAttribute(ATT_SESSION_USER, joueur);
		}
		else{
			session.setAttribute(ATT_SESSION_USER, null);
		}
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, joueur);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
