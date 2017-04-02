<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><%--<head> Элемент-контейнер для метаданных html-документа, таких как<title>, <meta>, <script>, <link>, <style> --%>
    <title>Title</title>
    <style>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3><a href="users.jsp">Users</a></h3>
<h3>Meal list</h3>
<h4><a href="meals?action=create">Create meal</a> </h4>
<section>

    <table border="1" cellpadding="8" cellspacing="0">
        <caption style="font-weight: bold; color: dodgerblue">Meals list</caption>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:formatDateTime(meal.dateTime)}--%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
        </c:forEach>
    </table>
</section>


</body>
</html>
