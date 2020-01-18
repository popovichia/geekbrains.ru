/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author igor
 */
public class ClientHost {
    private Socket socket;

    ClientHost(String serverAddress, int portNumber) {
        try {
            this.socket = new Socket(serverAddress, portNumber);
            new Channel(this.socket);
        } catch(IOException ioException1) {
            ioException1.printStackTrace();
        }
    }
}
