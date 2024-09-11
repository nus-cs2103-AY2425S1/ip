package bibi.gui;

import java.io.IOException;

import bibi.Bibi;
import javafx.application.Application;
import javafx.fxml.FXML;
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
    public static final String DEFAULT_FILE_PATH = "src/main/resources/data/list.txt";

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
            fxmlLoader.<MainWindow>getController().setDuke(bibi); // inject the Duke instance
            stage.show();
            assert false : "Failed to";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a new dialog box with user input and appends it to the
     * dialog container(VBox). Clears the input in the TextField afterwards.
     */
    @FXML
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(userInput.getText(), userImage),
                DialogBox.getBotDialogBox(bibi.getResponse(userInput.getText()), botImage));
        userInput.clear();
    }
}
