package java2.chat.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class JFXController {
    @FXML
    TextField textFieldServer;
    @FXML
    TextField textFieldPort;
    @FXML
    Label labelNickName;
    @FXML
    TextArea textAreaChat;
    @FXML
    TextField textFieldMessage;
    @FXML
    HBox upperPanel;
    @FXML
    HBox bottomPanel;
    @FXML
    TextField textFieldLogin;
    @FXML
    PasswordField textFieldPassword;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void connect() {
        try {
            String serverAddress = textFieldServer.getText();
            int port = Integer.valueOf(textFieldPort.getText());
            socket = new Socket(serverAddress, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String stringFromServer = dataInputStream.readUTF();
                            if (stringFromServer.startsWith("/authok")) {
                                changeUI(true);
                                break;
                            } else {
                                textAreaChat.appendText(stringFromServer + "\n");
                            }
                        }
                        while (true) {
                            String stringFromServer = dataInputStream.readUTF();
                            if (stringFromServer.equals("/serverClosed")) {
                                break;
                            }
                            textAreaChat.appendText(stringFromServer + "\n");
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
                        changeUI(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            dataOutputStream.writeUTF("/auth " + textFieldLogin.getText() + " " + textFieldPassword.getText());
            textFieldLogin.clear();
            textFieldPassword.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg() {
        try {
            dataOutputStream.writeUTF(textFieldMessage.getText());
            textFieldMessage.clear();
            textFieldMessage.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changeUI(boolean isAuthorized) {
        //labelNickName.setText(":");
        upperPanel.setVisible(!isAuthorized);
        upperPanel.setManaged(!isAuthorized);
        bottomPanel.setVisible(isAuthorized);
        bottomPanel.setManaged(isAuthorized);
    }
}
