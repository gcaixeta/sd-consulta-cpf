<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Consulta de Crédito</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container" style="max-width: 480px; margin-top: 5rem;">
    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="card-title mb-1">Consulta de Crédito</h4>
        <p class="text-muted mb-4">Servidor Central de Risco</p>
        <form action="consulta" method="post">
          <div class="mb-3">
            <label for="cpf" class="form-label">CPF do cliente</label>
            <input type="text" id="cpf" name="cpf" class="form-control" placeholder="000.000.000-00" required>
          </div>
          <button type="submit" class="btn btn-primary w-100">Consultar</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
