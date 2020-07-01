/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.cloudstorage.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author igor
 */
public class Client {
    
    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    
    public Client(Socket socket) {
        this.socket = socket;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException ioException) {
        }
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    public InputStream getInputStream() {
        return this.inputStream;
    }
    
    public OutputStream getOutputStream() {
        return this.outputStream;
    }
}
