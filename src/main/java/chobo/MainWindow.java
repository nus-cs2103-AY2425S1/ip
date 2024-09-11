package chobo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * The type Main window.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chobo chobo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/anon.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/anon.jpg"));

    /**
     * Instantiates a new Main window.
     */
    public MainWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        fxmlLoader.setRoot(this);  // Set this as the root node
        fxmlLoader.setController(this);  // Set this as the controller

        try {
            fxmlLoader.load();  // Load the FXML
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets chobo.
     *
     * @param chobo the chobo
     */
    public void setChobo(Chobo chobo) {
        this.chobo = chobo;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chobo.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

