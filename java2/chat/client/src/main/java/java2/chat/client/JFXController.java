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
    private Connection connection;
    public void connect() {
        this.connection = new Connection(this);
        this.connection.start();
        this.socket = connection.getSocket();
        this.dataInputStream = connection.getDataInputStream();
        this.dataOutputStream = connection.getDataOutputStream();
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (this.socket == null || this.socket.isClosed()) {
            connect();
        }
        try {
            this.dataOutputStream.writeUTF("/auth " + textFieldLogin.getText() + " " + textFieldPassword.getText());
            textFieldLogin.clear();
            textFieldPassword.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg() {
        try {
            this.dataOutputStream.writeUTF(textFieldMessage.getText());
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
