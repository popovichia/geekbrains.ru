/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author igor
 */
public class Channel {
    private Socket socket;
    private int id;
    Channel(Socket socket) {
        this.socket = socket;
        this.id = id;
        waitMessage();
        sendMessage();
    }
    private void waitMessage() {
        new Thread(new Runnable() {
        DataInputStream dataInputStream;
            @Override
            public void run() {
                try {
                    while(true) {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        System.out.println(dataInputStream.readUTF());
                    }
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                } finally {
                    try {
                        dataInputStream.close();
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }).start();        
    }
    private void sendMessage() {
        new Thread(new Runnable() {
        DataOutputStream dataOutputStream;
        String string;
        Scanner scanner = new Scanner(System.in);
            @Override
            public void run() {
                try {
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    while(true) {
                        string = scanner.next();
                        dataOutputStream.writeUTF(string);
                    }
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                } finally {
                    try {
                        dataOutputStream.close();
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }).start();        
    }
}
