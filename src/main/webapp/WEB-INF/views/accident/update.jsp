<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../header.jsp" />
<body>
<div class="container">
    <div class="row pt-3">
        <h1>Обновить инцедент</h1>
    </div>
    <div class="row pt-3">
        <form  action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
            <div class="form-group">
                <label>Название:</label>
                <input type='text' name='name' class="form-control" value="${accident.name}">
            </div>
            <div class="form-group">
                <label>Описание:</label>
                <input type='text' name='text' class="form-control" value="${accident.text}">
            </div>
            <div class="form-group">
                <label>Адрес:</label>
                <input type='text' name='address' class="form-control" value="${accident.address}">
            </div>
            <button type="submit" class="btn btn-primary">Обновить</button>
        </form>
    </div>
</div>
</body>
</html>
