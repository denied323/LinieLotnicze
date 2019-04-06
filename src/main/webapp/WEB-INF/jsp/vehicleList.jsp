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
	<jsp:param name="pageName" value="vehicleList"/>
</jsp:include>
<div id="main" class="card-body">
	<H1>LISTA POJAZDÓW</H1>
	<H3>Implementacja widoku z tagów JSTL(JSP Standard Tags Library)</H3>

    <form:form id="searchForm" modelAttribute="searchCommand">
        <div class="row">
            <div class="form-group col-md-6">
                <label for="phrase">z</label>
                <form:input path="phrase5" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase5" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group col-md-6">
                <label for="phrase">do:</label>
                <form:input path="phrase6" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase6" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group col-md-3">
                <label for="phrase">Cena od:</label>
                <form:input path="minPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="minPrice" cssClass="error text-danger" element="div"/>
            </div>
            <div class="form-group col-md-3">
                <label for="phrase">Cena do:</label>
                <form:input path="maxPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="maxPrice" cssClass="error text-danger" element="div"/>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-8"></div>

            <div class="form-group col-md-2">
                <a href="/vehicleList.html?all" class="btn btn-success">
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

    <c:if test="${empty vehicleListPage.content}">
        ${searchCommand.isEmpty() ? 'Lista pojazdów jest pusta':'Brak wyników wyszukiwania'}
    </c:if>

    <c:if test="${not empty vehicleListPage.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${vehicleListPage.totalElements} pojazdów
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${vehicleListPage.totalElements} pojazdów
            </c:otherwise>
        </c:choose>

		<c:set var="boundMin" value="${20000}"/>
		<c:set var="boundMax" value="${40000}"/>

		<table class="table table-striped">
			<thead>
			<tr>
				<th>Nr.lotu</th>
				<th>Klasa lotu</th>
				<th>Model samolotu</th>
				<th>Miejsce startu lotu</th>
				<th>Miejsce końca lotu</th>
				<th>Cena</th>
				<th>ilosc miejsc</th>
				<th>Data startu</th>
                <th>Czas startu</th>
                <th>Data końca lotu</th>
                <th>Czas końca</th>
                <security:authorize access="hasRole('ADMIN')">
				    <th>Opcje</th>
                </security:authorize>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${vehicleListPage.content}" var="vehicle">
				<tr>
					<td>
						<security:authorize access="isAuthenticated()">
							<a href="?id=${vehicle.id}">${vehicle.id}</a>
						</security:authorize>
						<security:authorize access="isAnonymous()">
							${vehicle.id}
						</security:authorize>
					</td>
					<td>${empty vehicle.vehicleClass.name?'Brak danych': vehicle.vehicleClass.name}</td>
					<td>${empty vehicle.vehicleName.name?'Brak danych': vehicle.vehicleName.name}</td>
					<td>${empty vehicle.start?'Brak danych': vehicle.start}</td>
					<td>${empty vehicle.destination?'Brak danych': vehicle.destination}</td>
					<td><fmt:formatNumber type="CURRENCY" value="${vehicle.price}"  currencySymbol="PLN"/></td>
					<td>${empty vehicle.places?'Brak danych': vehicle.places}</td>
					<td><fmt:formatDate  value="${vehicle.dateStart}"  type="date" timeStyle="small"/><td>
                    <td>${empty vehicle.startTime?'Brak danych': vehicle.startTime}</td>
                    <td><fmt:formatDate  value="${vehicle.dateEnd}"  type="date" timeStyle="small"/></td>
                    <td>${empty vehicle.endTime?'Brak danych': vehicle.endTime}</td>

                    <security:authorize access="hasRole('ADMIN')">
					<td>
                        <c:url var="deleteUrl" value="/vehicleList.html?did=${vehicle.id}&page=${vehicleListPage.number}&size=${vehicleListPage.size}" />
                        <c:url var="editUrl" value="/vehicleForm.html?id=${vehicle.id}" />
                        <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>
                        <a class="btn btn-success" href="${editUrl}">Edytuj</a>
					</td>
                    </security:authorize>
				</tr>
			</c:forEach>
			</tbody>
		</table>

        <c:set var="page" value="${vehicleListPage}" scope="request"/>
        <c:set var="mainUrl" value="vehicleList.html" scope="request"/>
        <jsp:include page="shared/pagination.jsp"/>

	</c:if>

</div>
<security:authorize access="hasRole('ADMIN')">
    <div>
        <a class="btn btn-success" href="vehicleForm.html">Dodaj Nowy</a>
    </div>
</security:authorize>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
