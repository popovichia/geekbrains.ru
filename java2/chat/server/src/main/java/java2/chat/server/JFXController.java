package java2.chat.server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class JFXController {
    
    @FXML
    HBox hboxStart;
    @FXML
    HBox hboxStop;
    @FXML
    TextField textFieldPort;
    @FXML
    TextArea textAreaServerLog;
    
    private ServerMain serverMain;
    
    public void startServer() {
        try {
            changeUI(true);
            int port = Integer.valueOf(textFieldPort.getText());
            this.serverMain = new ServerMain(port, this);
            this.serverMain.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void stopServer() {
        try {
            changeUI(false);
            this.serverMain.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void changeUI(boolean isServerStarted) {
        hboxStart.setVisible(!isServerStarted);
        hboxStart.setManaged(!isServerStarted);
        hboxStop.setVisible(isServerStarted);
        hboxStop.setManaged(isServerStarted);
    }
    public void writeLog(String message) {
        textAreaServerLog.appendText(message + "\n");
    }
}
