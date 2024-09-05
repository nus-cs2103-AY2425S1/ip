package bottle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Main.
 */
public class Main extends Application {
    /**
     * The constant DEFAULT_FILE_PATH.
     */
    private static final String DEFAULT_FILE_PATH = "duke/example.txt";
    /**
     * The Scroll pane.
     */
    private ScrollPane scrollPane;
    /**
     * The Dialog container.
     */
    private VBox dialogContainer;
    /**
     * The User input.
     */
    private TextField userInput;
    /**
     * The Send button.
     */
    private Button sendButton;
    /**
     * The Scene.
     */
    private Scene scene;
    /**
     * The User image.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    /**
     * The Bottle image.
     */
    private Image bottleImage = new Image(this.getClass().getResourceAsStream("/images/bottleImage.png"));
    /**
     * The Bottle.
     */
    private Bottle bottle = new Bottle("./data/bottle.txt");

    /**
     * Instantiates a new Main.
     *
     * @param filePath the file path
     */
    // Existing constructor
    public Main(String filePath) {
        // ...
    }

    /**
     * Instantiates a new Main.
     */
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Sets bottle.
     *
     * @param d the d
     */
    public void setBottle(Bottle d) {
        bottle = d;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBottle(bottle); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = bottle.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBottleDialog(dukeText, bottleImage)
        );
        userInput.clear();
    }
}
