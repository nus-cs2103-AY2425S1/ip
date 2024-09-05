package fishman.gui;

import fishman.Fishman;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents the MainWindow to be displayed to the user.
 */
public class MainWindow {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/koi.jpg"));
    private final Image fishmanImage = new Image(this.getClass().getResourceAsStream("/images/fish.jpg"));
    private Fishman fishman;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /**
     * Initializes the window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();

    }

    /**
     * Sets the Fishman instance and loads tasks from the save file. Any errors that occur during load are displayed
     * in the DialogBox.
     *
     * @param fishman The Fishman instance to be used.
     */
    public void setFishman(Fishman fishman) {
        this.fishman = fishman;
        String errorMessage = fishman.loadAndSaveTasks("load");
        if (errorMessage != null) {
            showLoadErrors(errorMessage);
        }
    }

    /**
     * Handles the user input by getting the response from Fishman and displaying the messages in the DialogBox. The
     * user input is cleared afterward.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fishman.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFishmanDialog(response, fishmanImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message in the DialogBox on launch.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Fishman\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getFishmanDialog(welcomeMessage, fishmanImage));
    }

    /**
     * Displays any error message from loading file data in the DialogBox on launch.
     */
    private void showLoadErrors(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getFishmanDialog(errorMessage, fishmanImage));
    }


}
