package jeriel.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeriel.Jeriel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        VBox root = fxmlLoader.load();

        // Provide the file path for Jeriel
        String filePath = "data/tasks.txt"; // Specify the path where your tasks file is saved
        Jeriel jerielInstance = new Jeriel(filePath); // Pass the file path to the Jeriel constructor

        // Get the controller and pass the Jeriel instance
        MainWindowController controller = fxmlLoader.getController();
        controller.setJeriel(jerielInstance);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Jeriel Chatbot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
