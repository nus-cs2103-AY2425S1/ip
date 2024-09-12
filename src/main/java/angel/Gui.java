package main.java.angel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for the Angel chatbot using JavaFX and FXML.
 * This class sets up the GUI components and initialises the Angel chatbot.
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
        assert stage != null : "Stage cannot be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/GuiWindow.fxml"));
            assert fxmlLoader != null : "FXMLLoader could not be initialized";

            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Failed to load the FXML file";

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            GuiWindow controller = fxmlLoader.getController();
            assert controller != null : "Controller for GuiWindow could not be found";
            controller.setAngel(angel); // Injects the Angel instance

            stage.show();
            assert stage.isShowing() : "Stage should be showing";
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "IOException occurred while loading FXML";
        }
    }
}
