import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import neon.Neon;
public class Main extends Application {
    private final Neon neon = new Neon("./data/data.txt");

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchor = loader.load();
            Scene scene = new Scene(anchor);
            stage.setScene(scene);

            loader.<MainWindow>getController().startNeon(neon);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
