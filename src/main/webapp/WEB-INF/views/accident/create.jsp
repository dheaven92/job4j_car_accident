<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../header.jsp" />
<body>
<div class="container">
    <div class="row pt-3">
        <h1>Добавить инцедент</h1>
    </div>
    <div class="row pt-3">
        <form action="<c:url value='/save'/>" method='POST'>
            <div class="form-group">
                <label>Название:</label>
                <input type='text' name='name' class="form-control" required>
            </div>
            <div class="form-group">
                <label>Описание:</label>
                <input type='text' name='text' class="form-control">
            </div>
            <div class="form-group">
                <label>Адрес:</label>
                <input type='text' name='address' class="form-control">
            </div>
            <div class="form-group">
                <label>Тип:</label>
                <select name="type.id" class="form-control">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Статьи:</label>
                <select name="ruleIds" multiple class="form-control">
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </form>
    </div>
</div>
</body>
</html>
