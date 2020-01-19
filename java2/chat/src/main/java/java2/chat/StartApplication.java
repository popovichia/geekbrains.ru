package java2.chat;

import java2.chat.server.ServerMain;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                new ServerMain();
            }
        });
        serverThread.start();
        Thread.sleep(500);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Window.fxml"));
        
        Scene scene = new Scene(root, 350, 375);
        scene.getStylesheets().add("/styles/Styles.css");        
        stage.setTitle("Chat");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}