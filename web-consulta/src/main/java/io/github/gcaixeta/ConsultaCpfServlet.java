package io.github.gcaixeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/consulta")
public class ConsultaCpfServlet extends HttpServlet {

  private static final String HOST_SERVIDOR_CENTRAL = "localhost";
  private static final int PORTA_SERVIDOR_CENTRAL = 2077;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String cpf = req.getParameter("cpf");
    req.setAttribute("cpf", cpf);

    try {
      boolean inadimplente = consultarServidorCentral(cpf);
      req.setAttribute("inadimplente", inadimplente);
    } catch (IOException ex) {
      req.setAttribute("erro", "Não foi possível consultar o Servidor Central de Risco");
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/resultado.jsp");
    dispatcher.forward(req, resp);
  }

  private boolean consultarServidorCentral(String cpf) throws IOException {
    try (Socket socket = new Socket(HOST_SERVIDOR_CENTRAL, PORTA_SERVIDOR_CENTRAL);
        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      saida.println(cpf);

      String resposta = entrada.readLine();
      if (resposta == null) {
        throw new IOException("Servidor Central de Risco não respondeu.");
      }

      // resposta no formato "cpf:true" ou "cpf:false", conforme BuscaCpf do servidor-central
      String situacao = resposta.substring(resposta.lastIndexOf(':') + 1);
      return Boolean.parseBoolean(situacao);
    }
  }
}
