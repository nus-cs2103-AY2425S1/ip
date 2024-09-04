package duke;

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
 * The MainWindow class serves as the controller for the main user interface of the Duke application.
 * It handles user interactions, including text input and button clicks,
 * and manages the dialog between the user and Duke.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sends a greeting from Duke to the dialog container when the application starts.
     * This method is called to display Duke's initial greeting message.
     */
    @FXML
    public void sendGreeting() {
        dialogContainer.getChildren().add(DialogBox.getDukeGreeting(
                duke.getGreeting(), dukeImage));
    }

    /**
     * Sets the stage for the main window. This is used to control the window, such as closing it.
     *
     * @param s The stage to set.
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Sets the Duke instance for this window.
     * This allows the window to interact with the Duke application logic.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!duke.isOnline()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage.close();
                Platform.exit();
            });
        }
    }
}
