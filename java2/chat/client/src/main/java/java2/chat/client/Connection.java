/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author igor
 */
public class Connection extends Thread {
    private JFXController jfxController;
    private String serverAddress;
    private int port;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public Connection(JFXController jfxController) {
        try {
            this.jfxController = jfxController;
            this.serverAddress = jfxController.textFieldServer.getText();
            this.port = Integer.valueOf(jfxController.textFieldPort.getText());
            this.socket = new Socket(this.serverAddress, this.port);
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                dataInputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
        
    }
    @Override
    public void run() {
        try {
            while (true) {
                String stringFromServer = dataInputStream.readUTF();
                if (stringFromServer.startsWith("/authok")) {
                    jfxController.changeUI(true);
                    break;
                } else {
                    jfxController.textAreaChat.appendText(stringFromServer + "\n");
                }
            }
            while (true) {
                String stringFromServer = dataInputStream.readUTF();
                if (stringFromServer.equals("/serverClosed")) {
                    break;
                }
                jfxController.textAreaChat.appendText(stringFromServer + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jfxController.changeUI(false);
        }
    }
    public Socket getSocket() {
        return this.socket;
    }
    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }
    public DataOutputStream getDataOutputStream() {
        return this.dataOutputStream;
    }
}
