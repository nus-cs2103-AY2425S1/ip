package GUI;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Main.Diego;
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

    private Diego diego;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image diegoImage = new Image(this.getClass().getResourceAsStream("/images/DaDiego.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the diego instance */
    public void setDiego(Diego d) {
        diego = d;
        dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diego.showWelcomeMessage(), diegoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing diego's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        // Display user input
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, userImage));

        if (userText.equalsIgnoreCase("Bye")) {
            // Display goodbye message and close the application after a delay
            dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diego.showGoodbyeMessage(), diegoImage));
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        } else {
            // Get and display Diego's response
            String diegoText = diego.getResponse(userText);
            dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diegoText, diegoImage));
        }

        userInput.clear();
    }
}
