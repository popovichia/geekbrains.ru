package java2.chat.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javafx.fxml.FXML;
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
    private File fileServerLog;
    
    public void initialize() {
        File dirServerLog = new File(".");
        Date nowDate = new Date();
        fileServerLog = new File("log_" + nowDate.getTime() + ".log");
        try {
            fileServerLog.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }        
        File[] listServerLogs = dirServerLog.listFiles();
        Arrays.sort(listServerLogs);
        for (File file : listServerLogs) {
            if (file.getName().startsWith("log") 
                    && file.getName().endsWith(".log")
                    && file.canRead()) {
                
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    byte[] buffer = new byte[4096];
                    while(fileInputStream.read(buffer) > 0 ) {
                        textAreaServerLog.appendText(new String(buffer, "UTF-8"));
                    }
                } catch(Exception ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
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
        message = message + "\n";
        textAreaServerLog.appendText(message);
        byte[] dataOut = message.getBytes();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileServerLog, true)) {
            fileOutputStream.write(dataOut);
        } catch(Exception ioException){
            ioException.printStackTrace();
        }
    }
}
