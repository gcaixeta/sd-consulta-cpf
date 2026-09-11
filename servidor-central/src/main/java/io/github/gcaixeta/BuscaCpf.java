package io.github.gcaixeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.net.Socket;

public class BuscaCpf extends Thread {
  private final Socket conn;
  private PrintWriter output;
  private BufferedReader input;

  private final CpfService cpfService;
  private final LogService log;

  
  public BuscaCpf(Socket conn, CpfService cpfs, LogService log) {
    this.conn = conn;
    this.cpfService = cpfs;
    this.log = log;
  }

  @Override
  public void run() {
    try {
      this.output = new PrintWriter(conn.getOutputStream(), true);
      this.input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String cpf = input.readLine();
      Boolean estaInadimplente = cpfService.cpfEstaInadimplente(cpf);
      log.cpfConsultado(cpf, estaInadimplente);

      output.println(cpf + ":" + estaInadimplente);
      System.out.println("Busca por cpf processado com sucesso: " + cpf);

    } catch (IOException ex) {
      System.out.println("Erro ao buscar cpf com conn: " + conn);
      ex.printStackTrace();
    } finally {
      try {conn.close();} catch (IOException ignored){};
    }

  }
  
}
