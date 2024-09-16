package xizi.chatbot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xizi.chatbot.Xizi;


//https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/package-summary.html
/**
 * Serves as the main class to load and start the application.
 */
public class XiziApp extends Application {

    private Xizi xizi = new Xizi("data/xizi.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(XiziApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Xizi Bot");
            fxmlLoader.<MainWindow>getController().setXizi(xizi); // inject the Xizi instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

