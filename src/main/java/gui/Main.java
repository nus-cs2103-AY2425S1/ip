package gui;

import java.io.IOException;

import casper.CasperBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private CasperBot casperBot = new CasperBot("chatbot.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("CasperBot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCasperBot(casperBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
