/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author igor
 */
public class ServerHost {
    private ServerSocket serverSocket;

    ServerHost(int portNumber) {
        try {
            this.serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started.");
            waitClient();
        } catch (IOException ioException2) {
            ioException2.printStackTrace();
        }
    }
    private void waitClient() {
        Socket socket;
        try {
                socket = serverSocket.accept();
                Channel channel = new Channel(socket);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
