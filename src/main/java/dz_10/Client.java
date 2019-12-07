package dz_10;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import static dz_10.Consts.welcome;

/**
 * in - поток чтения из сокета
 * out - поток чтения в сокет
 * inputUser - поток чтения с консоли
 * nickname - имя клиента, которое создается при входе в чат
 */
public class Client {
    //статики потому что финальная версия + не надо пробрасывать в каждый метод
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static BufferedReader inputUser;
    private static String nickname;
    private static String host = "localhost";
    private static int port = 8080;

    public static final String MAGICWORD = "quit";

    public static void main(String[] args) {
        try {
            socket = new Socket(host, port);
            //создаем потоки чтения и записи
            initStreams();
            //необходимо ввести никнейм в консоль
            readNickName();
            //поток получающий данные с сервера
            new Thread(() -> {
                readFromServer();//погасит все если ввести MAGICWORD
            }).start();
            //читаем сообщения из консоли и отправляем на сервер
            new Thread(() -> {
                readConsoleAndWriteToServer();
            }).start();
        } catch (IOException e) {
            closeClient();
        }
    }

    private static void initStreams() throws IOException {
        inputUser = new BufferedReader(new InputStreamReader(System.in));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private static void readConsoleAndWriteToServer() {
        while (true) {
            String userWord;
            try {
                userWord = inputUser.readLine();
                if (userWord.equalsIgnoreCase(MAGICWORD)) {
                    //out.write(getMessageTime() + nickname + "вышел");//на много потоке клиент уже убит на сервере
                    out.write(MAGICWORD);//сервер оповещен о выходе
                    out.flush();
                    closeClient();
                    break; // выходим из цикла если пришло "stop"
                } else {
                    //шлем на сервер
                    out.write(getMessageTime() + nickname + ": " + userWord + "\n");
                    out.flush();
                }
            } catch (IOException e) {
                closeClient();
            }
        }
    }

    private static String getMessageTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date()) + " ";
    }

    private static void readFromServer() {
        String str;
        try {
            while (true) {
                str = in.readLine();
                if (str.equalsIgnoreCase(MAGICWORD)) {
                    closeClient();
                    break;
                }
                System.out.println(str);
            }
        } catch (IOException e) {
            closeClient();
        }
    }

    private static void readNickName() throws IOException {
        System.out.print("Введите свое имя: ");
        nickname = inputUser.readLine();
        out.write(welcome + nickname + "\n");
        out.flush();
    }

    private static void closeClient() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }
}
