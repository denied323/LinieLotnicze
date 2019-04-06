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
    <c:param name="pageName" value="accessoryForm"></c:param>
</c:import>
<div id="main" class="card-body container">

    <form:form modelAttribute="accessory">

        <div class="form-group">
            <label for="name" class="bmd-label-floating">Nazwa akcesoria:</label>
            <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
            <form:errors path="name" cssClass="error text-danger" element="div"/>
        </div>

        <button type="submit" class="btn btn-primary btn-raised">Zapisz</button>
    </form:form>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>