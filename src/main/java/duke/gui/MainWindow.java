package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main window of the Duke application.
 * This class extends AnchorPane and controls the primary user interface.
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

    private Duke duke;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private boolean isIntroSent = false;

    /**
     * Initializes the main window.
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sends the introduction message from Duke.
     * This method adds Duke's greeting to the dialog container.
     */
    @FXML
    public void sendIntro() {
        dialogContainer.getChildren().add(DialogBox.getDukeIntro(
                duke.getGreeting(), dukeImage));
    }

    /**
     * Sets the Duke instance for this window.
     *
     * @param d The Duke instance to be used
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the Stage instance for this window.
     *
     * @param s The Stage instance to be used
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Handles user input.
     * This method is called when the user sends a message. It processes the input,
     * gets a response from Duke, and updates the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!duke.isRunning()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.close();
                Platform.exit();
            });
        }
    }
}
