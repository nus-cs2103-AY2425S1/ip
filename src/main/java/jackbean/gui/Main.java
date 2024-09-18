package jackbean.gui;

import java.io.IOException;

import jackbean.command.JackBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for JackBean using FXML.
 */
public class Main extends Application {

    private JackBean jackBean = new JackBean();

    /**
     * Starts the GUI.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param stage The stage to start the GUI on.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage should not be null.";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("JackBean");
            assert jackBean != null : "JackBean should not be null.";
            fxmlLoader.<MainWindow>getController().setJackBean(jackBean); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
