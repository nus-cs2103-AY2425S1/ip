package bobby;


import bobby.javafx.DialogBox;
import bobby.ui.Ui;
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
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Bobby bobby;

    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/homer.png"));
    private Image bobbyImage = new Image(this.getClass().getResourceAsStream("/images/ai.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the bobby instance */
    public void setBobby(Bobby b) {
        bobby = b;
        ui = new Ui();
        String helloMessage = ui.getGreetingMessage();
        dialogContainer.getChildren().add(DialogBox.getBobbyDialog(helloMessage, bobbyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bobby's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobby.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobbyDialog(response, bobbyImage)
        );
        userInput.clear();
        if (response.equals(ui.getExitMessage())) {
            // Delay the exit to allow the "bye" message to be displayed
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
