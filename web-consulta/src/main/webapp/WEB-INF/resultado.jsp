<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Resultado da Consulta</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container" style="max-width: 480px; margin-top: 5rem;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="card-title mb-4">Resultado da Consulta</h4>

        <c:choose>
          <c:when test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
          </c:when>
          <c:otherwise>
            <p class="mb-2">CPF consultado: <strong><c:out value="${cpf}"/></strong></p>
            <c:choose>
              <c:when test="${inadimplente}">
                <div class="alert alert-danger">Inadimplente</div>
              </c:when>
              <c:otherwise>
                <div class="alert alert-success">Regular</div>
              </c:otherwise>
            </c:choose>
          </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-secondary w-100">Nova consulta</a>
      </div>
    </div>
  </div>
</body>
</html>
