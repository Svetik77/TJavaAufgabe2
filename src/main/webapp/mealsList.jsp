<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mystyle3.css">
    <title>Meal list</title>

</head>
<body>
<h3 style="color: blue;">myMealsList.jsp</h3>


<section>
    <h2><a href="index.html">Home</a></h2>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <!-- Servlet?(if action.equals("create"))  -->
   <h2><a href="Meals?action=create">Add new Meal</a></h2>
    <hr>
    <table  >
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="com.ch.essen.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td> <a href="Meals?action=update&id=${meal.id }"> Update Meal </a> </td>
                <td> <a href="Meals?action=delete&id=${meal.id }"> Delete Meal </a> </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>