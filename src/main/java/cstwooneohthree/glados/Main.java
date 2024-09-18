package cstwooneohthree.glados;

import java.io.IOException;

import cstwooneohthree.glados.components.HistoryWindow;
import cstwooneohthree.glados.components.MainWindow;
import cstwooneohthree.glados.enums.UiType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Glados using FXML.
 */
public class Main extends Application {

    private Glados glados = new Glados(UiType.GRAPHICAL_USER_INTERFACE);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader mainWindowLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainPane = mainWindowLoader.load();

            FXMLLoader historyWindowLoader = new FXMLLoader(Main.class.getResource("/view/HistoryWindow.fxml"));
            AnchorPane historyPane = historyWindowLoader.load();
            HistoryWindow historyWindowController = historyWindowLoader.getController();

            Scene mainScene = new Scene(mainPane);
            Scene historyScene = new Scene(historyPane);

            mainScene.getStylesheets().add(this.getClass().getResource("/css/main.css").toExternalForm());
            stage.setScene(mainScene);

            stage.setMinHeight(450);
            stage.setMinWidth(800);
            stage.setMaxWidth(800);
            stage.setTitle("GLaDOS");

            mainWindowLoader.<MainWindow>getController().setGlados(glados, historyWindowController, historyScene); // inject the Glados instance
            historyWindowLoader.<HistoryWindow>getController().setMainScene(mainScene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
