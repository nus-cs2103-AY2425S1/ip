package oliver;

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

    private Oliver oliver;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ducks.jpg"));
    private Image oliverImage = new Image(this.getClass().getResourceAsStream("/images/oliver.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Meow! I'm Oliver, your purr-sonal task tracker!\nWhat can I do for you today?\n" +
                "Need help with your list, or maybe a reminder to chase your dreams... or a mouse?";
        dialogContainer.getChildren().add(
                DialogBox.getOliverDialog(greeting, oliverImage)
        );
    }

    /** Injects the Oliver instance */
    public void setOliver(Oliver oliver) {
        this.oliver = oliver;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Oliver's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = oliver.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOliverDialog(response, oliverImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            // Exit the application after 2 seconds of delay
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
