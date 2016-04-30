package com.projet.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Message {
	String contenu;
	String exp;
	public static String tchat="";

	public Message(String exp, String contenu){
		this.contenu = contenu;
		this.exp = exp;
	}

	public void afficher(String fichier){
		tchat = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("/home/remi/Documents/TIA/JeuD-Roll-Web/JeuDRollWeb/sources_XML/"+fichier);
			NodeList nListMessage = doc.getElementsByTagName("message");
			for(int i=0;i<nListMessage.getLength();i++){
				Node n = nListMessage.item(i);
				tchat += n.getAttributes().getNamedItem("exp").getNodeValue()+" : "+n.getTextContent()+"<br/>";
			}
		} catch (ParserConfigurationException |SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ajouter(String fichier){
		try{
			InputStream in = new FileInputStream("/home/remi/Documents/TIA/JeuD-Roll-Web/JeuDRollWeb/sources_XML/"+fichier); 
			InputStreamReader lecture = new InputStreamReader(in);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			File temp = File.createTempFile("temp",".tmp");
			PrintWriter out = new PrintWriter(temp);
			while ((ligne=buff.readLine()).compareTo("</listemessage>")!=0){
				out.println(ligne);
			}
			out.println("\t<message id=\"1\" exp=\""+this.exp+"\">"+this.contenu+"</message>");
			out.println("</listemessage>");
			File destination = new File("/home/remi/Documents/TIA/JeuD-Roll-Web/JeuDRollWeb/sources_XML/"+fichier); 
			temp.renameTo(destination);
			out.close();
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public static void creerFichier(int id_partie) throws IOException{
		File ficheMessageVide = new File("/home/sources_XML/message.xml");
		File newFicheMessage = new File("/home/sources_XML/message_"+id_partie+".xml");
		
		newFicheMessage.createNewFile();
		
		InputStream is = null;
		OutputStream os =null;
		try{
			is = new FileInputStream(ficheMessageVide);
			os = new FileOutputStream(newFicheMessage);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) > 0){
				os.write(buffer, 0, length);
			}
		}finally {
			is.close();
			os.close();
		}
	}
}
