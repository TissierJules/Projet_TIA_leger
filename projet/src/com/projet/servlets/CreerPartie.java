package com.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.beans.Partie;
import com.projet.forms.CreationPartieFormulaire;
import com.projet.dao.DAOFactory;
import com.projet.dao.PartieDao;
/**
 * Servlet implementation class CreerPartie
 */
@WebServlet("/CreerPartie")
public class CreerPartie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_PARTIE = "partie";
	public static final String ATT_FORM = "form";
	public static String VUE = "/principale.jsp";
    private PartieDao partieDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void init() throws ServletException{
		this.partieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPartieDao();
	}
    
    public CreerPartie() {
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
		CreationPartieFormulaire form = new CreationPartieFormulaire(partieDao);	
		
		Partie partie = form.inscrirePartie(request);
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_PARTIE, partie);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
