<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Szczegóły pojazdu</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>

<body class="card">
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="vehicleList"/>
</c:import>
<div id="main" class="card-body container">
<H1>Dane pojazdu</H1>
Id: <b>${vehicle.id}</b><br/>
Czas utworzenia wpisu: <b><fmt:formatDate value="${vehicle.createdDate}" type="both" timeStyle="medium"/></b><br/>
Marka: <b>${empty vehicle.name?'Brak danych': vehicle.name}</b><br/>
Model: <b>${empty vehicle.model?'Brak danych': vehicle.model}</b><br/>
Cena: <b><fmt:formatNumber type="CURRENCY" value="${vehicle.price}" currencySymbol="PLN"/></b><br/>
Uszkodzony?: <b>${vehicle.broken?'TAK':'NIE'}</b><br/>
Data produkcji <b><fmt:formatDate  value="${vehicle.productionDate}" type="date" timeStyle="medium"/></b><br/>
Typ pojazdu: <b>${vehicle.vehicleType.name}</b><br/>

<a class="btn btn-success" href="/vehicleList.html">Wstecz</a>
</div>
</body>
<jsp:include page="shared/footer.jsp"/>
</html>

