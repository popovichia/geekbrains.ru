/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.cloudstorage.server.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class InputChannelServer implements Runnable {
    
    private Socket socket;
    private boolean isStopped = false;
    
    public InputChannelServer(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {            
            InputStream inputStream = socket.getInputStream();
            while (!isStopped) {
                byte[] byteBuffer = new byte[4096];
                while (inputStream.available() > 0) {
                    inputStream.read(byteBuffer);
                    System.out.println(byteBuffer);
                }
            }
            inputStream.close();
        } catch (IOException ioException) {
        }
    }
    
    public void stop(boolean isStopped) {
        this.isStopped = true;
    }

}
