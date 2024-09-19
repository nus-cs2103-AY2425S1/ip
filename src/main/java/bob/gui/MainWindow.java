package bob.gui;

import static bob.Bob.Command.BYE;

import bob.Bob;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the main GUI of Bob.
 * This class manages the layout, user interactions, and display of dialogs between the user and Bob.
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

    private Bob bob;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bobImage.png"));

    /**
     * Initializes the main window and displays a welcome message from Bob when the application starts.
     * The scroll pane is enabled to scroll down automatically when new dialogs are added.
     * It also displays a welcome message when the application starts.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show welcome message when the chatbot starts
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Bob.startUp(), bobImage)
        );
    }

    /**
     * Injects the Bob instance into the controller.
     *
     * @param b The Bob instance used to generate responses and handle logic for the chatbot.
     */
    public void setBob(Bob b) {
        this.bob = b;
    }

    /**
     * Injects the stage into the controller.
     *
     * @param stage The primary stage of the application for window management.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Closes the GUI window.
     */
    public void closeWindow() {
        stage.close();
    }

    /**
     * Handles user input from the text field when the send button is pressed or the user hits enter.
     * It creates two dialog boxes: one echoing the user's input and the other containing Bob's response.
     * After processing, the user input is cleared.
     * If the user inputs "bye", the application will close automatically in 1 second.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        Bob.Command commandType = bob.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage, commandType)
        );
        userInput.clear();

        // Close the window in 1 second if the user inputs "bye"
        if (commandType.equals(BYE)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> closeWindow());
            delay.play();
        }
    }
}

