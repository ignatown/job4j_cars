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
<div class="container-fluid">
    <c:if test="${user != null}">
        <div class="btn-group" role="group">
            <a href="<%=request.getContextPath()%>/all" type="submit" class="btn btn-primary" role="button">На главную</a>
            <a href="<%=request.getContextPath()%>/cabinet" type="submit" class="btn btn-primary" role="button">Назад</a>
            <a href="<%=request.getContextPath()%>/add" type="submit" class="btn btn-primary" role="button">Добавить объявление</a>
            <a href="<%=request.getContextPath()%>/logout" type="submit" class="btn btn-warning" role="button">Выйти [<c:out value="${user.name}"/>]</a><br>
        </div>
    </c:if>
    <c:if test="${user == null}">
        <div class="btn-group" role="group">
        <a href="<%=request.getContextPath()%>/login" type="submit" class="btn btn-primary" role="button">Войти</a><br>
        </div>
    </c:if>
    <br>
    <div class="container-fluid">
            <h4>Новое объявление</h4>
            <form action="<%=request.getContextPath()%>/add" method="post">
                <div class="form-group col-3 p-1">
                    <label>Описание автомобиля</label>
                    <textarea class="form-control" name="descriptionAd" rows="5" placeholder="Введите описание"></textarea>
                </div>
                <div class="form-group col-3 p-1">
                    <label>Производитель</label>
                    <select class="form-select" name="brands" id="brandId">
                        <c:forEach items="${brands}" var="brand" >
                            <option value='<c:out value="${brand.id}"/>'>
                                <c:out value="${brand.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-3 p-1">
                    <label>Тип кузова</label>
                    <select class="form-select" name="bodies" id="bodyId">
                        <c:forEach items="${bodies}" var="body" >
                            <option value='<c:out value="${body.id}"/>'>
                                <c:out value="${body.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-3 p-1 mt-2">
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Добавить</button>
                </div>
            </form>
        </div>
</div>
</body>
</html>