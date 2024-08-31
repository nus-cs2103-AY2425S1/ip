package tars;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.util.Duration;
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

    private Tars tars;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image tarsImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Initializes the main window by setting up the scroll pane and displaying a welcome message.
     *
     * <p>This method is automatically called after the FXML fields have been populated.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Injects the TARS instance into the controller.
     *
     * @param t the TARS instance to be used by this controller.
     */
    public void setTars(Tars t) {
        tars = t;
    }

    /**
     * Handles user input by creating dialog boxes for the user's input and TARS's response.
     *
     * <p>This method retrieves the user's input from the text field, generates TARS's response,
     * and displays both in the dialog container. The input field is cleared after processing.
     * If the user inputs "bye", the application will exit after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tars.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTarsDialog(response, tarsImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Displays a welcome message in the dialog container when the application starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm TARS. What can I do for you today?";
        dialogContainer.getChildren().add(DialogBox.getTarsDialog(welcomeMessage, tarsImage));
    }
}
