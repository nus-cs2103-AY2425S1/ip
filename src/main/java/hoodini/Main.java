package hoodini;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Allows access to the chatbot
 */
public class Main extends Application {


    private Hoodini hoodini = new Hoodini();

    /**
     * Starts the application
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HoodiniMainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("HoodiniBot");
            stage.setScene(scene);
            fxmlLoader.<HoodiniMainWindow>getController().setHoodini(hoodini);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
