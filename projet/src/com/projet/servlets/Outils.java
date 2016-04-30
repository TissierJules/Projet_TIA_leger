package com.projet.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.projet.parser.Dessin;

/**
 * Servlet implementation class Outils
 */
@WebServlet("/Outils")
public class Outils extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Outils() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = (String)request.getParameter("type");
		String fichier = (String)request.getParameter("fichier");
		String source = (String)request.getParameter("source");
		String x = (String)request.getParameter("x");
		String y = (String)request.getParameter("y");
		String x2 = (String)request.getParameter("x2");
		String y2 = (String)request.getParameter("y2");
		
		Dessin d = new Dessin(type, source, x, y, x2, y2);
		d.modifier(fichier);
		d.afficher(fichier);
		response.sendRedirect("principale.jsp");


		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
