package fridayproject;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Friday friday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/Joy.png"));

    @FXML
    public void initialize() {

        // Assertions to ensure that the FXML elements are not null
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert dialogContainer != null : "fx:id=\"dialogContainer\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert userInput != null : "fx:id=\"userInput\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert userImage != null : "User image not found";
        assert fridayImage != null : "Friday image not found";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Friday instance */
    public void setFriday(Friday fri) {
        
        // Assertion to ensure that the Friday instance is not null
        assert fri != null : "Friday instance should not be null";
        friday = fri;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Assertion to ensure that the input is not null
        assert input != null : "Input should not be null";

        String response = friday.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, fridayImage)
        );
        userInput.clear();

        // Close the application if the user input is "bye"
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit()); // Exits program after the pause
            delay.play();
        }
    }
}