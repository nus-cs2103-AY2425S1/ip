import gui.MainWindow;
import impl.chatbot.Danny;
import impl.storage.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the application that extends JavaFX's Application class.
 * This class is responsible for initializing the chatbot, loading/saving data,
 * and setting up the JavaFX GUI.
 */
public class Main extends Application {
    private final Danny danny;

    /**
     * Constructor for the Main class.
     * Initializes Danny (the chatbot) and attempts to load previous tasks from storage.
     * If loading fails, it starts with a new list.
     */
    public Main() {
        danny = new Danny();
        Storage loader;
        try {
            loader = new Storage("src/main/java/data/tasks.txt", danny);
            System.out.println("Loading previous lists...");
            loader.loadTask();
            System.out.println("Load Completed. Welcome back :)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Save not found, starting off with new list.");
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * Sets up the primary stage and loads the FXML for the main window.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setVars(danny);  // inject the Danny instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application should stop, and provides a
     * place to prepare for application exit and destroy resources.
     * <p>
     * It attempts to save the current tasks to storage before the application closes.
     */
    @Override
    public void stop() {
        try {
            Storage loader = new Storage("src/main/java/data/tasks.txt", danny);
            loader.saveTask();
            System.out.println("Saving successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Saving unsuccessful :(");
        }
    }
}