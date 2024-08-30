package zaibot.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zaibot.Zaibot;

import java.io.IOException;

public class Main extends Application {

    private final Zaibot zaibot = new Zaibot();
    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("zAIbot");
            fxmlLoader.<MainWindow>getController().setZaibot(zaibot);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
