package pandabot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pandabot.main.PandaBot;

/**
 * Main class to launch the GUI for PandaBot using FXML.
 * This class extends Application and serves as the entry point
 * for the JavaFX application.
 */
public class Main extends Application {

    /**
     * Instance of PandaBot that will be used by the GUI.
     * The PandaBot is initialized with the file path to the saved data.
     */
    private final PandaBot pandaBot = new PandaBot("./data/PandaBot.txt");

    /**
     * The main entry point for all JavaFX applications. The start method is called after
     * the JavaFX application has been initialized and sets up the stage with the GUI components.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they are not primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPandaBot(pandaBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
