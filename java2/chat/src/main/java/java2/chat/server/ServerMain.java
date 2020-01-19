package java2.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ServerMain {
    private Map<String ,ClientHandler> clientsMap;
    public ServerMain() {
        clientsMap = new HashMap<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            AuthService.connect();
            serverSocket = new ServerSocket(8765);
            System.out.println("Сервер запущен!");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }
    public boolean isLoggedIn(String nickName) {
        return clientsMap.containsKey(nickName);
    }
    public void subscribe(String nickName, ClientHandler clientHandler) {
        clientsMap.put(nickName, clientHandler);
    }
    public void unsubscribe(String nickName, ClientHandler clientHandler) {
        clientsMap.remove(nickName);
    }
    public void sendMsg(String nickName, String msg) {
        clientsMap.get(nickName).sendMsg(msg);
    }
    public void sendMsg(String msg) {        
        for (Entry<String, ClientHandler> client : clientsMap.entrySet()) {
            client.getValue().sendMsg(msg);
        }
    }
}
