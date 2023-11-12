<%--
  Created by IntelliJ IDEA.
  User: Елизавета
  Date: 10.11.2023
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Заголовок</title>
    <link href="/webapp/css/styles.css" rel="stylesheet" type="text/css">
</head>


<div class="form-style-2">
    <div class="form-style-2-heading">
        Регистрация документа
    </div>
    <form method="post" action="/documents">
        <label for="documentType">Тип документа
            <input class="input-field" type="text" id="documentType" name="documentType">
        </label>
        <label for="organization">Организация
            <input class="input-field" type="text" id="organization" name="organization">
        </label>
        <label for="description">Описание
            <input class="input-field" type="text" id="description" name="description">
        </label>
        <label for="patient">Пациент
            <input class="input-field" type="text" id="patient" name="patient">
        </label>
        <input type="submit" value="Добавить документ">
    </form>
</div>


<div class="form-style-2">
    <table>
        <tr>
            <th>Тип документа</th>
            <th>Дата</th>
            <th>Организация</th>
            <th>Описание</th>
            <th>Пациент</th>
            <th>Статус</th>
        </tr>
        <c:forEach items="${documentsFromServer}" var="documentDTO">
            <tr>
                <td>${documentDTO.documentType}</td>
                <td>${documentDTO.date}</td>
                <td>${documentDTO.organization}</td>
                <td>${documentDTO.description}</td>
                <td>${documentDTO.patient}</td>
                <td>${documentDTO.status}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
