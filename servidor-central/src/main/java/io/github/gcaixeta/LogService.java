package io.github.gcaixeta;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LogService
 */
public class LogService {
  private static final DateTimeFormatter FORMATO_TIMESTAMP_ARQUIVO = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
  private static final String ARQUIVO_LOG = "logs-" + LocalDateTime.now().format(FORMATO_TIMESTAMP_ARQUIVO) + ".txt";
  private static LogService instance;
  private PrintWriter pw;

  private LogService() {
    try {
      pw = new PrintWriter(new FileWriter(ARQUIVO_LOG), true);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static synchronized LogService getInstance() {
    if (instance == null) {
      instance = new LogService();
    }

    return instance;
  }

  public void cpfConsultado(String cpf, boolean estaInadimplente) {
    pw.println("[LogService.cpfConsultado]" + " - " + LocalDateTime.now() + " - Cpf consultado: cpf: " + cpf + "  inadimplente: " + estaInadimplente);
  }
}

