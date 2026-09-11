package io.github.gcaixeta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * CpfService
 */
public class CpfService {
  private static final String arquivoCpfInadimplente = "inadimplentes.txt";
  private static CpfService instance;

  private CpfService() {
  }

  public static CpfService getInstance() {
    if (instance == null) {
      instance = new CpfService();
    }
    return instance;
  }

  public boolean cpfEstaInadimplente(String cpf) throws IOException {
    String alvo = normalizar(cpf);
    try (Stream<String> stream = Files.lines(Path.of(arquivoCpfInadimplente))) {
      return stream.anyMatch(line -> normalizar(line).equals(alvo));
    }
  }

  private String normalizar(String valor) {
    return valor == null ? "" : valor.replaceAll("\\D", "");
  }
}
