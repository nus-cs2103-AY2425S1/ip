package bibi.gui;

import java.io.IOException;

import bibi.Bibi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The bibi.gui.Main class for JavaFx
 */
public class Main extends Application {
    public static final String DEFAULT_FILE_PATH = "./data/list.txt";

    private Bibi bibi;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jfif"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jfif"));

    // Existing constructor
    public Main(String filePath) {
        bibi = new Bibi(filePath);
    }
    // Overloaded constructor (allows JavaFx to continue using empty constructor)
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bibi");
            stage.setScene(scene);

            // Prevent Resizing beyond a certain limit
            double minWidth = 544;
            stage.setMinWidth(minWidth);
            double minHeight = 600;
            stage.setMinHeight(minHeight);

            fxmlLoader.<MainWindow>getController().setBibi(bibi); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
