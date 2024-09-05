package ipman;

import java.io.IOException;

import ipman.ui.JavaFxUi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    private final JavaFxUi ui;
    private final IpMan ipMan;

    public Main(JavaFxUi ui) {
        this.ui = ui;
        this.ipMan = new IpMan(ui);
    }

    public Main() {
        this(new JavaFxUi());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
