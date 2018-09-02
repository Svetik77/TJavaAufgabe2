<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.* "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/mealList.css" />"
	rel="stylesheet">
<meta charset="UTF-8">
<title>mealEdit.jsp</title>
</head>
<body>
	<h3>mealEdit.jsp</h3>
	<!-- --------go to method POST in servlet Meals---MealServlet------ -->
	<section>
		<h2>
			<a href="index.jsp"> Home </a>
		</h2>
		<h3>Edit Meal List</h3>
		<hr>
		<jsp:useBean id="meal" type="com.ch.essen.model.Meal" scope="request"></jsp:useBean>
		<form action="Meals" method="post" title="MealsServlet">

			<input type="hidden" name="id" value="${meal.id }">
			<dl>
				<dt>DateTime:</dt>
				<dd>
			<!-- only html 5 -->
					<input type="datetime-local" value="${meal.dateTime }" name="dateTime">
				</dd>
			</dl>
			
			<dl>
				<dt>Description:</dt>
				<dd>
					<input type="text" value="${meal.description }" name="description">
				</dd>
			</dl> 
			
			<dl>
				<dt>Calories:</dt>
				<dd>
					<input type="number" value="${meal.calories }" name="calories" >
				</dd>
			</dl>
			
			<button type="submit">Save</button>
			<!-- rolling back history -->
			<button onclick="window.history.back()">Cancel</button>
			
		</form>
	</section>
</body>
</html>