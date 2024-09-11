import java.io.IOException;

import UI.Delphi;
import UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Delphi delphi = new Delphi("../ip/src/main/HardDisk.txt");

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            //line to help with closing application
            fxmlLoader.<MainWindow>getController().setDelphi(delphi);  // inject the Delphi instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//More code to be added here later



