package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static List<ClientConnection> clients = new ArrayList<>();


    public void listen(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started!");
        Logger.log("Server started! port - " + port);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    clients.add(new ClientConnection(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }


    private static class ClientConnection extends Thread {

        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;

        public ClientConnection(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            start();
        }

        public void run() {

            String name = null;
            try {
                name = in.readLine();
                Logger.log("В чат добавился клиент - " + name);
                sendMessageAllConnection(name + " добавился в чат");
                String message;
                while (true) {
                    message = in.readLine();
                    if (message.equals("exit")) {
                        break;
                    }
                    sendMessageAllConnection(name + ": " + message);
                    Logger.log(name + ": " + message);
                }

            } catch (IOException e) {
            } finally {
                sendMessageAllConnection(name + " вышел из чата");
                close();
                Logger.log(name + " вышел из чата");
            }
        }

        private void sendMessage(String message) {
            try {
                out.write(message + "\n");
                out.flush();
            } catch (IOException ignored) {
            }
        }

        private void close() {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendMessageAllConnection(String message) {
            synchronized (clients) {
                clients.forEach(ClientConnection -> ClientConnection.sendMessage(message));
            }
        }

    }
}
