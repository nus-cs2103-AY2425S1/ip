import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snowy.Snowy;

public class Main extends Application {
    private final Snowy snowy = new Snowy("data/snowy.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainLayout = fxmlLoader.load();

            assert mainLayout != null;
            
            Scene scene = new Scene(mainLayout);

            scene.getStylesheets().add("/css/main.css");
            scene.getStylesheets().add("/css/dialogBox.css");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSnowy(snowy);
            fxmlLoader.<MainWindow>getController().setInitialMessage();

            stage.setMinHeight(400);
            stage.setMinWidth(300);
            stage.setTitle("Snowy");
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
