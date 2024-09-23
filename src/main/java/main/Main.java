package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /**
     * The {@code Main} class is the entry point for the ProYapper application.
     * It sets up the main window using JavaFX and initializes the {@code ProYapper} instance.
     */
    private ProYapper proYapper = new ProYapper("./data/ProYapper.txt");

    /**
     * Starts the JavaFX application, sets up the main window, and loads the FXML layout.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ProYapper");
            primaryStage.show();

            MainWindow controller = loader.getController();
            controller.setProYapper(proYapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method which launches the JavaFX application.
     *
     * @param args command-line arguments passed during the program startup.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
