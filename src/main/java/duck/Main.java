package duck;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duck.components.MainWindow;


/**
 * Represents a GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String PATH_MAIN_WINDOW = "/view/MainWindow.fxml";
    private static final String IMAGE_DUCK = "/images/DaDuck.png";
    private final Duck duck = new Duck();

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(PATH_MAIN_WINDOW));

            setUpAppIconAndTitle(stage);
            setUpScene(stage, fxmlLoader);
            setUpDimensions(stage);
            setUpDuck(fxmlLoader);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpDuck(FXMLLoader fxmlLoader) {
        fxmlLoader.<MainWindow>getController().setDuck(duck); // inject the Duck instance
    }

    private static void setUpDimensions(Stage stage) {
        stage.setMinHeight(220);
        stage.setMinWidth(417);
    }

    private static void setUpScene(Stage stage, FXMLLoader fxmlLoader) throws IOException {
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
    }

    private void setUpAppIconAndTitle(Stage stage) {
        Image image = new Image(this.getClass().getResourceAsStream(IMAGE_DUCK));
        stage.setTitle("Duck");
        stage.getIcons().add(image);
    }
}
