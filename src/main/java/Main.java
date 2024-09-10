import blitz.Blitz;
import components.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Blitz blitz = new Blitz("src/main/data/blitz.txt");

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(blitz);
        mainWindow.load();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
