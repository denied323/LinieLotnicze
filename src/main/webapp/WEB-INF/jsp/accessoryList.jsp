<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Lista pojazdów</title>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css?<%=new java.util.Random().nextLong()%>" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>
<body class="card">
<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="accessoryList"/>
</jsp:include>
<div id="main" class="card-body">
    <H1>LISTA POJAZDÓW</H1>
    <H3>Implementacja widoku z tagów JSTL(JSP Standard Tags Library)</H3>

    <form:form id="searchForm" modelAttribute="searchCommand">
        <div class="row">
            <div class="form-group col-md-6">
                <label for="phrase5">Szukana fraza:</label>
                <form:input path="phrase5" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase5" cssClass="error text-danger" element="div"/>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-8"></div>

            <div class="form-group col-md-2">
                <a href="/accessoryList.html?all" class="btn btn-success">
                    <span class="glyphicon glyphicon-refresh"></span> Pokaż wszystko
                </a>
            </div>

            <div class="form-group col-md-2">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Wyszukaj
                </button>
            </div>
        </div>
    </form:form>

    <c:if test="${empty accessoryListPage.content}">
        ${searchCommand.isEmpty() ? 'Lista pojazdów jest pusta':'Brak wyników wyszukiwania'}
    </c:if>

    <c:if test="${not empty accessoryListPage.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${accessoryListPage.totalElements} pojazdów
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${accessoryListPage.totalElements} pojazdów
            </c:otherwise>
        </c:choose>

        <c:set var="boundMin" value="${20000}"/>
        <c:set var="boundMax" value="${40000}"/>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>nazwa</th>
                <security:authorize access="hasRole('ADMIN')">
                    <th>Opcje</th>
                </security:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accessoryListPage.content}" var="accessory">
                <tr>
                    <td>
                        <security:authorize access="isAuthenticated()">
                            <a href="?id=${accessory.id}">${accessory.id}</a>
                        </security:authorize>
                        <security:authorize access="isAnonymous()">
                            ${accessory.id}
                        </security:authorize>
                    </td>
                    <td>${empty accessory.name?'Brak danych': accessory.name}</td>
                    <security:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:url var="deleteUrl" value="/accessoryList.html?did=${accessory.id}&page=${accessoryListPage.number}&size=${accessoryListPage.size}" />
                        <c:url var="editUrl" value="/accessoryForm.html?id=${accessory.id}" />
                        <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>
                        <a class="btn btn-success" href="${editUrl}">Edytuj</a>
                    </td>
                    </security:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:set var="page" value="${accessoryListPage}" scope="request"/>
        <c:set var="mainUrl" value="accessoryList.html" scope="request"/>
        <jsp:include page="shared/pagination.jsp"/>

    </c:if>

</div>
<security:authorize access="hasRole('ADMIN')">
    <div>
        <a class="btn btn-success" href="accessoryForm.html">Dodaj Nowy</a>
    </div>
</security:authorize>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
