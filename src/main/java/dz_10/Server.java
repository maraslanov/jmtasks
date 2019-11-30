package dz_10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * многопользовательский чат
 *
 * serverList потоки -соединения с клиентами,
 * каждое из них содержит свой поток вв/вывода и сокет, который закрывается по сигналу из чата
 */
public class Server {
    public static final Integer SERVER_PORT = 8080;
    public static List<ServerListener> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(SERVER_PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerListener(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}