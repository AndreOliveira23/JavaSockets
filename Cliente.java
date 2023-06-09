package client;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Obtendo IP de servidores disponíveis para conexão...");

        // Define o número máximo de threads simultâneas
        int maxThreads = 10;

        // Cria um pool de threads
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);

        for (int i = 100; i <= 110; i++) {
            final String host = "192.168.0." + i;

            executor.execute(() -> {
                try {
                    // Conecta ao servidor na máquina local (localhost) e porta 1234
                    Socket socket = new Socket(host, 1234);

                    // Cria um leitor e um escritor para comunicação com o servidor
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    // Envia uma mensagem para o servidor
                    out.println(String.valueOf(InetAddress.getLocalHost()));

                    // Lê a resposta do servidor
                    String resposta = in.readLine();
                    System.out.println(resposta);

                    // Fecha as conexões
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    // Tratamento de erro, se necessário
                }
            });
        }

        // Encerra o pool de threads
        executor.shutdown();

        try {
            // Aguarda todas as threads terminarem
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Tratamento de erro, se necessário
        }

        System.out.println("Busca finalizada!");
    }
}
