<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="header.jsp" />
<body>
<div class="container">
   <div class="row pt-3">
      <h1>Войти</h1>
   </div>
   <c:if test="${not empty errorMessage}">
      <div class="row pt-3">
         <div class="alert alert-danger">
            ${errorMessage}
         </div>
      </div>
   </c:if>
   <c:if test="${not empty successMessage}">
      <div class="row pt-3">
         <div class="alert alert-success">
               ${successMessage}
         </div>
      </div>
   </c:if>
   <div class="row pt-3">
      <form action="<c:url value='/login'/>" method='POST'>
         <div class="form-group">
            <label>Логин:</label>
            <input type='text' name='username' class="form-control" required>
         </div>
         <div class="form-group">
            <label>Пароль:</label>
            <input type='password' name='password' class="form-control" required>
         </div>
         <button type="submit" class="btn btn-primary">Войти</button>
      </form>
   </div>
</div>
</body>
</html>
