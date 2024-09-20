package espresso.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Loads the FXML layout, sets up the primary stage (window), and shows it.
     *
     * @param primaryStage The main window of the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout);
            primaryStage.setTitle("Espresso");
            primaryStage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that launches the application.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}