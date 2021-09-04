<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.RequestDispatcher" %>


<% 
	RequestDispatcher rd = request.getRequestDispatcher("/index.do");
	String errorMessage = (String)request.getAttribute("errorMessage");
	System.out.println("error message : "+errorMessage);
	request.setAttribute("errorMessage", errorMessage);
	rd.forward(request, response); 
%>
