package java2.chat.server;

import java2.chat.server.services.ClientService;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java2.chat.server.entity.User;

public class ServerMain extends Thread {
    private ArrayList<ClientService> clientsServices;
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private JFXController jfxController;
    private boolean isStopped = false;
    public ServerMain(int port, JFXController jfxController) {
        this.clientsServices = new ArrayList<>();
        this.port = port;
        this.jfxController = jfxController;
        this.jfxController.writeLog("Сервер запущен.\n"
                + "Для подключения к серверу, используйте параметры:\n"
                + "Сервер - localhost, порт - " + port);
    }
    public boolean isLoggedIn(String nickName) {
        boolean result = false;
        for (ClientService cs : clientsServices) {
            if (nickName.equals(cs.getUser().getNickName())) {
                result = true;
            }
        }
        return result;
    }
    public void subscribe(ClientService clientService) {
        clientsServices.add(clientService);
    }
    public void unsubscribe(ClientService clientService) {
        clientsServices.remove(clientService);
    }
    public void sendMsg(ClientService clientServiceFrom, String message) {        
        for (ClientService cs : clientsServices) {
            if (!cs.getBlackList().getBlackList().contains(clientServiceFrom.getUser().getNickName())) {
                cs.sendMsg(clientServiceFrom.getUser().getNickName() + ": " + message);
            }
        }
    }
    public void sendMsg(ClientService clientServiceFrom, String clientServiceTo, String message) {        
        for (ClientService cs : clientsServices) {
            if (cs.getUser().getNickName().equals(clientServiceTo)) {
                cs.sendMsg(clientServiceFrom.getUser().getNickName() + ": " + message);
            }
        }
    }
    public void addUserToBlackList(ClientService clientService, String blockedUser) {
        for (ClientService cs : clientsServices) {            
            if (cs.getUser().getNickName().equals(blockedUser)) {
                clientService.getBlackList().addUserToBlackList(cs.getUser());
            }
        }        
    }
    public void stopServer() {
        isStopped = true;
        try {
            if(this.socket != null) {
                this.socket.close();
            }
            if(this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.jfxController.writeLog("Сервер остановлен.");
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
            while (!isStopped) {
                Thread.sleep(100);
                this.socket = serverSocket.accept();
                ClientService clientService = new ClientService(this, socket, this.jfxController);
                clientService.start();
                this.jfxController.writeLog("Подключился клиент." + socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
