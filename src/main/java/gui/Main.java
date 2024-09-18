package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wenjigglybot.WenJigglyBot;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private WenJigglyBot wenJigglyBot = new WenJigglyBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("WenJigglyBot");
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setWenJigglyBot(wenJigglyBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}