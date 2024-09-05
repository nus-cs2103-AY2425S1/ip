package atlas.ui;

import atlas.Atlas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
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

    private Atlas atlas;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image atlasImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Atlas instance, loads tasks and displays the welcome message */
    public void setAtlas(Atlas a) {
        atlas = a;
        this.handleWelcome();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Atlas's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = atlas.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAtlasDialog(response, atlasImage)
        );
        userInput.clear();
    }

    /**
     * Loads tasks and displays welcome text.
     */
    private void handleWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getAtlasDialog(atlas.init(), atlasImage)
        );
    }

    /**
     * Exits the application.
     */
    public static void handleExit() {
        Platform.exit();
    }
}
