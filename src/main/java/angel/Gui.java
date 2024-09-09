package main.java.angel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for the Angel chatbot using JavaFX and FXML.
 * This class sets up the GUI components and initializes the Angel chatbot.
 */
public class Gui extends Application {

    private Angel angel = new Angel();

    /**
     * Starts the GUI application.
     * This method is called when the application is launched and sets up the main stage.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<GuiWindow>getController().setAngel(angel); // inject the Angel instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
