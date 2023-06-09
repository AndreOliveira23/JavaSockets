package server.application;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.*;
import java.util.Random;
import java.math.*;

public class Server {

    public static void main(String[] args) throws Exception {
while(true) {
    try {
        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Aguardando conexão do cliente....");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado!!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        //Le msg client
        String mensagem = in.readLine();
        System.out.println("Broadcast recebido pelo cliente em:" + mensagem);
        System.out.println("Enviando resposta deste servidor ("+String.valueOf(InetAddress.getLocalHost())+")");
        out.println(String.valueOf(InetAddress.getLocalHost()));

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    } catch (IOException e) {

    }
}
    }
}
