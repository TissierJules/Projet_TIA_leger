package com.projet.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dessin {
	public static String dessin="<div style=\"position:absolute; left:20px; top:20px; z-index:1\"><img src=\"/home/sources_image/"+"taverne.jpg"+"\" /></div>";

	String type;
	String image;
	String x;
	String y;
	String x2;
	String y2;

	public Dessin(String type, String image, String x, String y, String x2, String y2) {
		this.type = type;
		this.image = image;
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	public static void creerFichier(int id, int id_carte){
		try{
			InputStream in = new FileInputStream("/home/sources_XML/carte.xml"); 
			InputStreamReader lecture = new InputStreamReader(in);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne, image;
			if (id_carte == 1){
				image = "taverne.jpg";
			}else{
				image = "donjon.png";
			}
			File temp = File.createTempFile("temp",".tmp");
			PrintWriter out = new PrintWriter(temp);
			Pattern pattern = Pattern.compile("<fond image=\"\">");
			Matcher matcher;
			
			while ((ligne=buff.readLine())!=null){
				matcher = pattern.matcher(ligne);
				if(matcher.find()){
					out.println("  <fond image=\""+image+"\">");
				}else{
				out.println(ligne);
				}
			}

			File destination = new File("/home/sources_XML/carte_"+id+".xml"); 
			temp.renameTo(destination);
			out.close();
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public void afficher(String fichier){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		String courant="";
		dessin="";
		int zindex=1;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("/home/sources_XML/"+fichier);
			Node nNodeFond = doc.getElementsByTagName("fond").item(0);
			NodeList nListToken = doc.getElementsByTagName("token");
			Node[] nNodeToken = new Node[nListToken.getLength()];
			for(int i=0;i<nListToken.getLength();i++){
				nNodeToken[i]=nListToken.item(i);
			}
			NodeList nListFleche = doc.getElementsByTagName("fleche");
			Node[] nNodeFleche = new Node[nListFleche.getLength()];
			for(int i=0;i<nListFleche.getLength();i++){
				nNodeFleche[i]=nListFleche.item(i);
			}
			NodeList nListMasque = doc.getElementsByTagName("masque");
			Node[] nNodeMasque = new Node[nListMasque.getLength()];
			for(int i=0;i<nListMasque.getLength();i++){
				nNodeMasque[i]=nListMasque.item(i);
			}
			courant = "<div style=\"position:absolute; left:20px; top:20px; z-index:"+zindex+"\"><img src=\"/home/sources_image/"+((Element)nNodeFond).getAttribute("image")+"\" /></div>";
			dessin+=courant+"\n";
			zindex++;

			for(int i=0;i<nListToken.getLength();i++){
				courant = "<div style=\"position:absolute; left:"+((Element)nNodeToken[i]).getAttribute("x")+"px; top:"+((Element)nNodeToken[i]).getAttribute("y")+"px; z-index:"+zindex+"\"><img src=\"/home/sources_image/"+((Element)nNodeToken[i]).getAttribute("image")+"\" /></div>";
				dessin+=courant+"\n";
			}

			for(int i=0;i<nListFleche.getLength();i++){
				courant = "<div style=\"position:absolute; left:"+((Element)nNodeFleche[i]).getAttribute("x")+"px; top:"+((Element)nNodeFleche[i]).getAttribute("y")+"px; z-index:"+zindex+"\"><img src=\"/home/sources_image/"+((Element)nNodeFleche[i]).getAttribute("image")+"\" /></div>";
				dessin+=courant+"\n";
			}

			for(int i=0;i<nListMasque.getLength();i++){
				int largeur = Integer.parseInt(((Element)nNodeMasque[i]).getAttribute("x2"))-Integer.parseInt(((Element)nNodeMasque[i]).getAttribute("x1"));
				int hauteur = Integer.parseInt(((Element)nNodeMasque[i]).getAttribute("y2"))-Integer.parseInt(((Element)nNodeMasque[i]).getAttribute("y1"));
				courant = "<div style=\"position:absolute; left:"+((Element)nNodeMasque[i]).getAttribute("x1")+"px; top:"+((Element)nNodeMasque[i]).getAttribute("y1")+"px; z-index:"+zindex+"\"><img src=\"/home/sources_image/"+((Element)nNodeMasque[i]).getAttribute("image")+"\"  width="+largeur+"px; height="+hauteur+"px; /></div>";
				dessin+=courant+"\n";
			}

		} catch (ParserConfigurationException |SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifier(String fichier){
		try{
			InputStream in = new FileInputStream("/home/sources_XML/"+fichier); 
			InputStreamReader lecture = new InputStreamReader(in);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			File temp = File.createTempFile("temp",".tmp");
			PrintWriter out = new PrintWriter(temp);
			if (type.equals("token")){
				while ((ligne=buff.readLine()).indexOf("</listetoken>")==-1){
					out.println(ligne);
				}
				out.println("      <token image='"+image+"' x='"+x+"' y='"+y+"' />");
				out.println("    </listetoken>");
				while ((ligne=buff.readLine())!=null){
					out.println(ligne);
				}
			}
			if (type.equals("fleche")){
				while ((ligne=buff.readLine()).indexOf("</listefleche>")==-1){
					out.println(ligne);
				}
				out.println("      <fleche image='"+image+"' x='"+x+"' y='"+y+"' />");
				out.println("    </listefleche>");
				while ((ligne=buff.readLine())!=null){
					out.println(ligne);
				}
			}
			if (type.equals("masque")){
				while ((ligne=buff.readLine()).indexOf("</listemasque>")==-1){
					out.println(ligne);
				}
				if (Integer.parseInt(x2) < Integer.parseInt(x)){
					String tmp = x2;
					x2 = x;
					x = tmp;
				}
				if (Integer.parseInt(y2) < Integer.parseInt(y)){
					String tmp = y2;
					y2 = x;
					y = tmp;
				}
				
				out.println("      <masque image='"+image+"' x1='"+x+"' y1='"+y+"' x2='"+x2+"' y2='"+y2+"'/>");
				out.println("    </listemasque>");
				while ((ligne=buff.readLine())!=null){
					out.println(ligne);
				}
			}
			if (type.equals("rm")){
				int posX = -1, posY = -1;
				Pattern pattern = Pattern.compile("[0-9]+");
				Matcher matcher;
				ligne=buff.readLine();
				out.println(ligne);
				while ((ligne=buff.readLine())!=null){
					posX = -1;
					posY = -1;
					matcher = pattern.matcher(ligne);
					if (matcher.find()) posX = Integer.parseInt(matcher.group(0));
					if (matcher.find()) posY = Integer.parseInt(matcher.group(0));
					if (posX != -1){
						if ((posX>Integer.parseInt(x))||(posX+20<Integer.parseInt(x))||(posY>Integer.parseInt(y))||(posY+20<Integer.parseInt(y))){
							out.println(ligne);
						}
					}else out.println(ligne);
				}
			}
			File destination = new File("/home/sources_XML/"+fichier); 
			temp.renameTo(destination);
			out.close();
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
