/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.cloudstorage.server.services;

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
    private InputChannelServer inputChannelServer;
    private OutputChannelServer outputChannelServer;
    
    public Client(Socket socket) {
        this.socket = socket;
        this.inputChannelServer = new InputChannelServer(this.socket);
        this.outputChannelServer = new OutputChannelServer(this.socket);        
        new Thread(inputChannelServer).start();
        new Thread(outputChannelServer).start();
    }
    
    public Socket getSocket() {
        return this.socket;
    }
    
    @Override
    public String toString() {
        return this.socket.getLocalAddress()
                + ":" +
                this.socket.getPort();
    }
}
