package com.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.dao.DAOFactory;
import com.projet.dao.JoueurDao;

/**
 * Servlet implementation class Choix
 */
@WebServlet("/Choix")
public class Choix extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "joueur";
	public static final String VUE = "/principale.jsp";
	private JoueurDao joueurDao;
       
	
	public void init() throws ServletException{
		this.joueurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getJoueurDao();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Choix() {
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
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}

}
