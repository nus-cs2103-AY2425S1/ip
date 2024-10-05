package rob;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
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

    private Rob rob;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image robImage = new Image(this.getClass().getResourceAsStream("/images/rob.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greet = ui.getGreet();
        dialogContainer.getChildren().addAll(
                DialogBox.getRobDialog(greet, robImage)
        );
        userInput.clear();
    }

    /** Injects the Rob instance */
    public void setRob(Rob r) {
        rob = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RobException {
        String input = userInput.getText();
        String response = rob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRobDialog(response, robImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> {
                    Stage stage = (Stage) dialogContainer.getScene().getWindow();
                    stage.close();
                });
                delay.play();
            });
        }
    }
}
