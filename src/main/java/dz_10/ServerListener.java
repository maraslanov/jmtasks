package dz_10;

import java.io.*;
import java.net.Socket;

import static dz_10.Consts.MAGICWORD;
import static dz_10.Consts.welcome;

/**
 * поток слушатель сервера (общий чат)
 * socket сокет, через который сервер общается с клиентом, отсылает всем сообщение которые было напечатано
 * прекращает общаться когда печатаешь quit
 */
public class ServerListener extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String identify;//по идее может быть любой класс для определения пользователя

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
            setIdentify(text.substring(welcome.length()));//клиент не должен называться пустой строкой, todo
            while (true) {
                text = in.readLine();
                if (MAGICWORD.equalsIgnoreCase(text)) {
                    switchOff();
                    break;
                }
                //отправляем только слушателю идентифай
                if (text.indexOf(("\"" + getIdentify() + " ")) > 0) {
                    senIfPersonMessage(text);
                    //continue;
                } else {
                    System.out.println("Общий чат: " + text);
                    //пересылаем сообщение всем остальным в общем чате
                    for (ServerListener thread : Server.serverList) {
                        sendText(text);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //закрываем все потоки?
            switchOff();
        }


    }

    private void senIfPersonMessage(String text) {
        String message = text.substring(text.indexOf("\"") + 1);
        String forWhom = message.substring(0, getIdentify().length());
        message = message.substring(message.indexOf(getIdentify()+1) + getIdentify().length());
        for (ServerListener thread : Server.serverList) {
            if (thread.getIdentify().equalsIgnoreCase(forWhom)) {
                sendText("->" + getIdentify() + ": " + message);
                continue;
            }
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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
