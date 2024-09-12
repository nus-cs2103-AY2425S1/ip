package friday.ui;

import friday.Friday;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tonystark.jpg"));
    private Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/Friday.png"));

    /**
     * Initializes the main window by binding the scroll pane's vertical value property
     * to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Friday instance into the main window.
     *
     * @param f The Friday instance to be used by the main window.
     */
    public void setFriday(Friday f) {
        assert f != null : "Friday instance should not be null";
        friday = f;
        dialogContainer.getChildren().add(DialogBox.getFridayDialog(friday.getResponse("hi"), fridayImage));
    }

    /**
     * Handles user input by creating two dialog boxes: one echoing the user input
     * and the other containing Friday's reply. Appends them to the dialog container
     * and clears the user input after processing. Exits the application if the response
     * contains the word "EXIT".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";

        if (input.equals("bye")) {
            sayGoodbyeAndExit(input);
            return;
        }

        String response = friday.getResponse(input);
        assert response != null : "Response should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFridayDialog(response, fridayImage)
        );

        userInput.clear();
    }

    /**
     * Displays a goodbye message and exits the application after a delay.
     */
    private void sayGoodbyeAndExit(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFridayDialog(friday.getResponse(input), fridayImage)
        );
        PauseTransition delay = new PauseTransition(Duration.seconds(0.75));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
