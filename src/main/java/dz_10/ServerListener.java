package dz_10;

import java.io.*;
import java.net.Socket;

import static dz_10.Consts.MAGICWORD;

/**
 * поток слушатель сервера (общий чат)
 * socket сокет, через который сервер общается с клиентом, отсылает всем сообщение которые было напечатано
 * прекращает общаться когда печатаешь quit
 */
public class ServerListener extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String text;
        try {
            //первое сообщение
            text = in.readLine();
            sendText(text);
            while (true) {
                text = in.readLine();
                if (MAGICWORD.equalsIgnoreCase(text)) {
                    switchOff();
                    break;
                }
                System.out.println("Общий чат: " + text);
                //пересылаем сообщение всем остальным в общем чате
                for (Thread thread : Server.serverList) {
                    sendText(text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //закрываем все потоки?
            switchOff();
        }


    }

    private void sendText(String text) {
        try {
            out.write(text + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void switchOff() {
        try {
            if (!socket.isClosed()) {//чтобы не ругалось
                socket.close();
                in.close();
                out.close();
                for (ServerListener listener : Server.serverList) {
                    if (listener.equals(this))
                        listener.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}
