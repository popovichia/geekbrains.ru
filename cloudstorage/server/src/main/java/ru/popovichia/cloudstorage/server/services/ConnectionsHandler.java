/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.cloudstorage.server.services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import ru.popovichia.cloudstorage.server.FXMLController;

/**
 *
 * @author igor
 */
public class ConnectionsHandler implements Runnable {
    
    private FXMLController fxmlController = null;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private boolean isStopped = false;
    private Client client = null;
    
    public ConnectionsHandler(FXMLController fxmlController, ServerSocket serverSocket, boolean isStopped) {
        this.serverSocket = serverSocket;
        this.isStopped = isStopped;
        this.fxmlController = fxmlController;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        while (!isStopped) {
            try {
                fxmlController.addMessageToLog("Сервер ожидает соединение...\n");
                socket = serverSocket.accept();
                fxmlController.addMessageToLog("Соединение установлено.\n"
                        + "    Подключился клиент: "
                        + socket.getInetAddress().getHostAddress() + "\n");
                client = new Client(socket);
                fxmlController.addClientToList(client);
            } catch (IOException ioException) {
            }
        }        
    }
    
    public void stop(boolean isStopped) {
        this.isStopped = isStopped;
    }
}
