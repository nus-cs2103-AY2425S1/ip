package chobo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Chobo chobo = new Chobo();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setChobo(chobo);  // Inject the Chobo instance

        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("Chobo Chatbot");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




