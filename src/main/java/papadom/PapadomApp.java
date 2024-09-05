package papadom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PapadomApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            primaryStage.setTitle("Papadom Chatbot");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
    To run:
    ./gradlew clean build
    ./gradlew run
     */
}
