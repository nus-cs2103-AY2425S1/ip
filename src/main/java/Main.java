import java.io.IOException;

import babygronk.BabyGronk;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application for GUI of BabyGronk.
 */
public class Main extends Application {

    private BabyGronk babyGronk = new BabyGronk("./data/BabyGronk.txt");

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            primaryStage.setTitle("BabyGronk");
            primaryStage.setMinHeight(220);
            primaryStage.setMinWidth(417);
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBabyGronk(babyGronk);
            fxmlLoader.<MainWindow>getController().sayHi();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
