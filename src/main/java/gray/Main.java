package gray;

import static gray.Constants.FILEPATH_DATA_TASKS;
import static gray.Constants.FILEPATH_IMAGE_GRAY;
import static gray.Constants.FILEPATH_IMAGE_USER;

import java.io.File;

import gray.gui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Gray.
 */
public class Main extends Application {
    private final Image userImage = new Image(Main.class.getResourceAsStream(FILEPATH_IMAGE_USER));
    private final Image dukeImage = new Image(Main.class.getResourceAsStream(FILEPATH_IMAGE_GRAY));
    private final Tasks tasks = new Tasks(new File(FILEPATH_DATA_TASKS));
    private final Gray gray = new Gray(tasks);

    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = new MainWindow(userImage, dukeImage, gray::respond);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
        mainWindow.addGrayDialog(gray.welcome());
    }
}
