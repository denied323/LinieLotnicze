<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="css/bootstrap-datetimepicker.css" rel="stylesheet">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>
<body class="card">
<c:import url="shared/header.jsp">
	<c:param name="pageName" value="vehicleForm"></c:param>
</c:import>
<div id="main" class="card-body container">

		<form:form modelAttribute="vehicle">
            <div class="form-group">
                <label for="vehicleClass.id" class="bmd-label-floating">Typ Pojazdu:</label>
                <form:select path="vehicleName.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
                    <form:option value="-1">--wybierz typ pojazdu--</form:option>
                    <form:options items="${vehicleNames}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors path="vehicleName.id" cssClass="error text-danger" element="div"/>
            </div>
            <div class="form-group">
				<label for="start" class="bmd-label-floating">Miejsce startu:</label>
                <form:input path="start" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="start" cssClass="error text-danger" element="div"/>
			</div>
            <div class="form-group">
                <label for="destination" class="bmd-label-floating">Miejsce końca:</label>
                <form:input path="destination" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="destination" cssClass="error text-danger" element="div"/>
            </div>
			<div class="form-group">
                <label for="price" class="bmd-label-floating">Cena:</label>
                <form:input path="price" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="price" cssClass="error text-danger" element="div"/>
			</div>
            <div class="form-group">
                <label for="places" class="bmd-label-floating">Miejsca:</label>
                <form:input path="places" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="places" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group">
                <label for="dateStart" class="bmd-label-floating">Data początku lotu:</label>
                <form:input path="dateStart" cssClass="form-control" type="date" cssErrorClass="form-control is-invalid"/>
                <form:errors path="dateStart" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group">
                <label for="startTime" class="bmd-label-floating">Data początku lotu:</label>
                <form:input path="startTime" cssClass="form-control" type="time" cssErrorClass="form-control is-invalid"/>
                <form:errors path="startTime" cssClass="error text-danger" element="div"/>
            </div>


<div class="form-group">
                <label for="dateEnd" class="bmd-label-floating">Data końca:</label>
                <form:input path="dateEnd" cssClass="form-control" type="date" cssErrorClass="form-control is-invalid"/>
                <form:errors path="dateEnd" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group">
                <label for="endTime" class="bmd-label-floating">Data początku lotu:</label>
                <form:input path="endTime" cssClass="form-control" type="time" cssErrorClass="form-control is-invalid"/>
                <form:errors path="endTime" cssClass="error text-danger" element="div"/>
            </div>



            <div class="form-group">
                <label for="vehicleClass.id" class="bmd-label-floating">Typ Pojazdu:</label>
                <form:select path="vehicleClass.id" cssClass="form-control" cssErrorClass="form-control is-invalid">
                    <form:option value="-1">--wybierz typ pojazdu--</form:option>
                    <form:options items="${vehicleClasses}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors path="vehicleClass.id" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group">
                <label class="bmd-label-floating">Wyposażenie pojazdu:</label>
                <form:checkboxes path="accessories" element="div class='checkbox' style='left:25px;'" items="${accessoryList}" itemLabel="name" itemValue="id"/>
                <form:errors path="accessories" cssClass="error text-danger" element="div"></form:errors>
            </div>

			<button type="submit" class="btn btn-primary btn-raised">Zapisz</button>
		</form:form>

</div>
			<jsp:include page="shared/footer.jsp"/>
</body>
</html>



