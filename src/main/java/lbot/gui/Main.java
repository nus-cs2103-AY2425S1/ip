package lbot.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lbot.LBot;
import lbot.gui.controllers.MainWindow;



/**
 * A GUI for LBot using FXML.
 */
public class Main extends Application {

    private LBot lbot = new LBot("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("LBot");
            MainWindow mw = fxmlLoader.<MainWindow>getController();
            mw.setLBot(lbot); // inject the LBot instance
            mw.greeting();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
