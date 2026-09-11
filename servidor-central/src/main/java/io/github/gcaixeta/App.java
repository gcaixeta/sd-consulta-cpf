package io.github.gcaixeta;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
  private static final int PORTA = 2077;
  
  public static void main(String[] args) {
    try (var socket = new ServerSocket(PORTA);) {
      while (true) {
        var conn = socket.accept();
        Thread buscaCpfTread = new BuscaCpf(conn, CpfService.getInstance(), LogService.getInstance());
        buscaCpfTread.start();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
