package killjoy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import killjoy.main.KillJoy;

public class Main extends Application {

    private KillJoy kj = new KillJoy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setKj(kj);
            kj.loadTasks();
            stage.show();

            MainWindow mw = fxmlLoader.getController();
            mw.welcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
