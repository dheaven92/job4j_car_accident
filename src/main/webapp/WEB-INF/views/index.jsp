<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <div class="row pt-3">
        <h1>Инцеденты</h1>
    </div>
    <div class="row pt-3">
        <a href="<c:url value='/create'/>" class="btn btn-primary">Добавить инцидент</a>
    </div>
    <div class="row pt-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Название</th>
                <th scope="col">Описание</th>
                <th scope="col">Адрес</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td><c:out value="${accident.id}"/></td>
                    <td><c:out value="${accident.name}"/></td>
                    <td><c:out value="${accident.text}"/></td>
                    <td><c:out value="${accident.address}"/></td>
                    <td>
                        <a href="<c:url value='/update?id=${accident.id}'/>">
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
