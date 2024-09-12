package momo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Create an instance of MainWindow
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();

            // Set MainWindow as both root and controller
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.setController(mainWindow);

            AnchorPane ap = fxmlLoader.load(); // Load the FXML
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the Duke instance
            fxmlLoader.<MainWindow>getController().setMomo();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}