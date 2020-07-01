/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.cloudstorage.server.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author igor
 */
public class ConnectionsHandler implements Runnable {
    
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private boolean isStopped = false;
    private TextArea taLog = null;
    private Client client = null;
    
    public ConnectionsHandler(ServerSocket serverSocket, boolean isStopped, TextArea taLog) {
        this.serverSocket = serverSocket;
        this.isStopped = isStopped;
        this.taLog = taLog;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        while (!isStopped) {
            try {
                taLog.appendText("Сервер ожидает соединение...\n");
                socket = serverSocket.accept();
                taLog.appendText("Соединение установлено.\n"
                        + "    Подключился клиент: "
                        + socket.getInetAddress().getHostAddress() + "\n");
                client = new Client(socket);
            } catch (IOException ioException) {
            }
        }        
    }
    
    public void stop(boolean isStopped) {
        this.isStopped = isStopped;
    }
}
