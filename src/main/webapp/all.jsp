<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Job4j-Cars</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="/scripts.js"></script>
</head>
<body>
<div style="text-align: center;">
    <h3>Job4j-Cars</h3>
    <h5>Площадка продажи автомобилей.</h5>
</div>
<div class="container-fluid" >
    <c:if test="${user != null}">
        <div class="btn-group" role="group">
            <a href="<%=request.getContextPath()%>/cabinet" type="submit" class="btn btn-primary" role="button">Личный кабинет</a><br>
            <a href="<%=request.getContextPath()%>/logout" type="submit" class="btn btn-warning" role="button">Выйти [<c:out value="${user.name}"/>]</a><br>
        </div>
    </c:if>
    <c:if test="${user == null}">
        <div class="btn-group" role="group">
            <a href="<%=request.getContextPath()%>/login" type="submit" class="btn btn-primary" role="button">Войти</a><br>
        </div>
    </c:if>
    <h3>Объявления</h3>
    <ul class="list-group" >
        <c:forEach items="${ads}" var="a">
            <li class="list-group-item">
                <img src="<c:url value='/download?name=${a.user.id}${a.id}'/>" width="100px" height="100px"/><br>
                <c:out value="${a.description}" /><br>
                <c:choose>
                    <c:when test="${a.status}"><b>Продано</b></c:when>
                    <c:when test="${!a.status}">В продаже</c:when>
                </c:choose><br>
                Производитель: <c:out value="${a.brand.name}" /><br>
                Тип кузова: <c:out value="${a.body.name}" /><br>
                Продавец: <c:out value="${a.user.name}" /><br>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>