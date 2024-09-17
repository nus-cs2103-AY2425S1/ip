package alex.javafx;

import alex.Alex;
import alex.Pair;
import alex.Ui;
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
 * Controller for the main GUI of the chatbot application.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Alex alex;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/Alex.png"));

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value to the height of the dialog container
     * and setting the prompt text for the user input field.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.userInput.setPromptText("Your wish is my command");
    }

    /**
     * Sets the Alex instance for the controller and initializes the dialog container with a welcome message from Alex.
     *
     * @param d The Alex instance to be used by the controller.
     */
    public void setAlex(Alex d) {
        alex = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(ui.showWelcome(), alexImage, "")
        );
    }

    public void showErrorMsgOnStart(Exception e) {
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(ui.showError(e), alexImage, "Error")
        );
    }

    /**
     * Handles user input by creating and displaying two dialog boxes: one for the user's input and one for Alex's
     * response. Clears the user input field after processing. If the command type indicates an exit command, a delay
     * is introduced before the application exits.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Pair<String, String> pair = alex.getResponse(input);
        String response = pair.getFirst();
        String commandType = pair.getSecond();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(response, alexImage, commandType)
        );
        userInput.clear();

        if (commandType.equals("ExitCommand")) {
            // Create a PauseTransition to delay the exit
            exitApplication(1);
        }
    }

    public static void exitApplication(int duration) {
        PauseTransition delay = new PauseTransition(Duration.seconds(duration));
        delay.setOnFinished(event -> Platform.exit()); // Exit after the delay

        delay.play(); // Start the delay
    }
}

