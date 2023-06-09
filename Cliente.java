package client;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Obtendo IP de servidores disponíveis para conexão...");
        for(int i = 100;i<=110;i++) {
            String host = "192.168.0."+i;
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
                System.out.println(resposta.substring(resposta.indexOf("/")+1));

                // Fecha as conexões
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                continue;
            }
        }
        System.out.println("Busca finalizada!");
    }
}
