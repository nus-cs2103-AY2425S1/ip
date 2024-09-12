package echoa;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point to the application.
 */

public class Main extends Application {
    /**
     * The main method is the entry point to the application.
     * It catches any file related exception and handles them.
    */
    @Override
    public void start(Stage stage) {
        String filePath = "./data/echoa.txt";
        Echoa echoa = new Echoa(filePath);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEchoa(echoa);  // inject the Echoa instance
            stage.show();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("System failed. Please contact administrator.");
        }
    }
}
