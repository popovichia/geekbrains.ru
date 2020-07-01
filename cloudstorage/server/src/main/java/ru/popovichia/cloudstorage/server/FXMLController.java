package ru.popovichia.cloudstorage.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ru.popovichia.cloudstorage.server.service.ConnectionsHandler;

public class FXMLController implements Initializable {
    
    @FXML
    private Label lServerIP;
    @FXML
    private TextField tfServerPort;   
    @FXML
    private Button bStart;
    @FXML
    private TextArea taLog;
    @FXML
    private ListView lvDirs;
    @FXML
    private ListView lvConnectedUsers;
    
    private ServerSocket serverSocket = null;
    private ConnectionsHandler clientConnection = null;
    private boolean isStopped = false;
    
    @FXML
    private void handleStartServerMouseClick(MouseEvent mouseEvent) {
        if (bStart.getText().equals("Start")) {
            int serverPort = -1;
            try {
                serverPort = Integer.parseInt(tfServerPort.getText());
            } catch (NumberFormatException numberFormatException) {

            }
            if (serverPort >= 0 && serverPort <= 65535) {
                try {
                    serverSocket = new ServerSocket(serverPort);
                    bStart.setText("Stop");
                    tfServerPort.setEditable(false);
                    taLog.appendText("Сервер запущен. IP: "
                            + lServerIP.getText()
                            + tfServerPort.getText() + ".\n");
                    clientConnection = new ConnectionsHandler(serverSocket, isStopped, taLog);
                    new Thread(clientConnection).start();
                } catch (IOException ioException) {

                }
            }
        } else if (bStart.getText().equals("Stop")) {
            if (serverSocket != null) {
                try {
                    clientConnection.stop(true);
                    serverSocket.close();
                    bStart.setText("Start");
                    tfServerPort.setEditable(true);
                    taLog.appendText("Сервер остановлен. IP: "
                            + lServerIP.getText()
                            + tfServerPort.getText() + ".\n");
                } catch (IOException ioException) {
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InetAddress serverInetAddress = InetAddress.getLocalHost();
            lServerIP.setText(serverInetAddress.getHostAddress() + ":");
            File serverDir = new File("./serverside");
            if (!serverDir.exists() || !serverDir.isDirectory()) {
                serverDir.mkdir();
            }
            taLog.appendText("Приложение запущенно.\n"
                    + "    Рабочая директория: "
                    + serverDir.getAbsolutePath() + "\n");
            ObservableList<String> itemsObservableList = FXCollections.observableArrayList(serverDir.list());
            lvDirs.setItems(itemsObservableList.sorted());
        } catch (UnknownHostException unknownHostException) {
        }
    }
}
