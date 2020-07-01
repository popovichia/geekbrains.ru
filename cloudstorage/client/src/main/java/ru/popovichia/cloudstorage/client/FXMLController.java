package ru.popovichia.cloudstorage.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField tfServerIP; 
    @FXML
    private TextField tfServerPort;
    @FXML
    private TextField tfLogin;    
    @FXML
    private PasswordField pfPassword;    
    @FXML
    private Button bLogin;    
    @FXML
    private Label lClientStatus;
    
    private Socket socket = null;
    
    @FXML
    private void handleLvClientMouseClick(MouseEvent mouseEvent) {
    }

    @FXML
    private void handleLvServerMouseClick(MouseEvent mouseEvent) {
    }
    
    @FXML
    private void handleBLoginMouseClick(MouseEvent mouseEvent) {
        try {
            if (socket == null || socket.isClosed()) {
                socket = new Socket(tfServerIP.getText(), Integer.parseInt(tfServerPort.getText()));
            }
        } catch (IOException ioException) {
        }
        if (socket != null && bLogin.getText().equals("Login")) {
            System.out.println("socket: " + socket.getLocalSocketAddress());
            lClientStatus.setText("Connected.");
            tfServerIP.setEditable(false);
            tfServerPort.setEditable(false);
            tfLogin.setEditable(false);
            pfPassword.setEditable(false);
            bLogin.setText("Logout");
        } else if (socket != null && bLogin.getText().equals("Logout")) {
            try {
                socket.close();
            } catch (IOException ioException) {
            }
            lClientStatus.setText("Disconnected.");
            tfServerIP.setEditable(true);
            tfServerPort.setEditable(true);
            tfLogin.setEditable(true);
            pfPassword.setEditable(true);
            bLogin.setText("Login");            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
