package hypebot.ui;

import hypebot.main.HypeBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UiGuiMain extends Application {
    private HypeBot hypeBot = new HypeBot("./src/main/data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UiGuiMain.class.getResource("/view/UiGuiMainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            fxmlLoader.<UiGuiMainWindow>getController().setHypeBot(hypeBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
