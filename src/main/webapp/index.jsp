<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.RequestDispatcher" %>


<% 
	RequestDispatcher rd = request.getRequestDispatcher("/index.do");
	rd.forward(request, response); 
%>
