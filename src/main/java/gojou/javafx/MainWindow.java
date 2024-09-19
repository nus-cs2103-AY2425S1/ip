package gojou.javafx;

import gojou.Gojou;
import gojou.Pair;
import gojou.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI of the chatbot application.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Gojou gojou;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image gojouImage = new Image(this.getClass().getResourceAsStream("/images/Gojou4.png"));

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value to the height of the dialog container
     * and setting the prompt text for the user input field.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.userInput.setPromptText("Let's see what you've got!");
    }

    /**
     * Sets the Gojou instance for the controller and initializes the dialog container with a welcome message from
     * Gojou.
     *
     * @param gojou The Gojou instance to be used by the controller.
     */
    public void setGojou(Gojou gojou) {
        this.gojou = gojou;
        dialogContainer.getChildren().addAll(
                DialogBox.getGojouDialog(ui.showWelcome(), gojouImage, "")
        );
    }

    public void showErrorMsgOnStart(Exception e) {
        dialogContainer.getChildren().addAll(
                DialogBox.getGojouDialog(ui.showError(e), gojouImage, "Error")
        );
    }

    /**
     * Handles user input by creating and displaying two dialog boxes: one for the user's input and one for Gojou's
     * response. Clears the user input field after processing. If the command type indicates an exit command, a delay
     * is introduced before the application exits.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Pair<String, String> pair = gojou.getResponse(input);
        String response = pair.getFirst();
        String commandType = pair.getSecond();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGojouDialog(response, gojouImage, commandType)
        );
        userInput.clear();

        if (commandType.equals("ExitCommand")) {
            // Create a PauseTransition to delay the exit
            exitApplication(3);
        }
    }

    public static void exitApplication(int duration) {
        PauseTransition delay = new PauseTransition(Duration.seconds(duration));
        delay.setOnFinished(event -> Platform.exit()); // Exit after the delay

        delay.play(); // Start the delay
    }
}

