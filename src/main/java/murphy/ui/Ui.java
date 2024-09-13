package murphy.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import murphy.Murphy;


public class Ui extends Application {
    private Murphy murphy = new Murphy("./data/murphy.txt", this);
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMurphy(murphy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showText(String text) {
        fxmlLoader.<MainWindow>getController().showText(text);
    }

    public void showError(String error) {
        fxmlLoader.<MainWindow>getController().showError(error);
    }
}