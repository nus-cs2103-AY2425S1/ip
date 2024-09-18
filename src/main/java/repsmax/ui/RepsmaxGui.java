package repsmax.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repsmax.logic.Repsmax;

public class RepsmaxGui extends Application {

    private Repsmax repsmax;

    @Override
    public void start(Stage primaryStage) throws Exception {
        repsmax = new Repsmax("src/data.txt");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Parent root = fxmlLoader.load();

        MainWindow controller = fxmlLoader.getController();
        controller.setRepsmax(repsmax);

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("Repsmax");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

