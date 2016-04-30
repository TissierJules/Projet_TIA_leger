<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.jdom2.output.Format"%>
<%@page import="org.jdom2.output.XMLOutputter"%>
<%@page import="org.jdom2.Element"%>
<%@page import="org.jdom2.Document"%>
<%@page import="org.jdom2.input.SAXBuilder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tchat</title>
</head>
<body>
	<% 
		SAXBuilder sxb = new SAXBuilder();
		Document doc = sxb.build(new File("message.xml"));
		Element racine = doc.getRootElement();
		List<Element> list = racine.getChildren();
		String id;
		if (list.size()==0){
			id = "0";
		}else{
			id = ""+(Integer.parseInt(list.get(list.size()-1).getAttributeValue("id"))+1);
		}
		Element nouveau = new Element("message");
		nouveau.setAttribute("id", id);
		nouveau.setAttribute("exp", "jules");
		nouveau.addContent("toto");
		list.add(nouveau);
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(doc, new FileOutputStream("message.xml"));
	%>
</body>
</html>