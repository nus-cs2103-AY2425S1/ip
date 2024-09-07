package cookie.gui;


import java.io.IOException;

import cookie.Cookie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main application class for launching the cookie GUI application.
 */
public class Main extends Application {

    private Cookie cookie = new Cookie();

    @Override
    public void start(Stage stage) {
        stage.setTitle("cookie");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCookie(cookie);
            stage.setOnCloseRequest(event -> handleCloseRequest());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCloseRequest() {
        cookie.handleQuit();
    }
}
