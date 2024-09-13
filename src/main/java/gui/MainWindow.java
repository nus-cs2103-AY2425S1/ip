package gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utils.Diego;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image diegoImage = new Image(this.getClass().getResourceAsStream("/images/DaDiego.png"));

    /**
     * Initializes the controller. This method is called automatically after the FXML
     * file has been loaded. It binds the scrollPane's vertical scroll value to the
     * dialogContainer's height property, so the scrollPane will always show the latest
     * content.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Diego instance.
     *
     * @param d The Diego instance to be used by the controller.
     */
    public void setDiego(Diego d) {
        diego = d;
        dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diego.showWelcomeMessage(), diegoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Diego's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        assert userText != null : "User input text should not be null";

        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, userImage));

        if ("Bye".equalsIgnoreCase(userText)) {
            showGoodbyeAndExit();
        } else {
            String diegoText = diego.getResponse(userText);
            dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diegoText, diegoImage));
        }

        userInput.clear();
    }

    private void showGoodbyeAndExit() {
        dialogContainer.getChildren().add(DialogBox.getDiegoDialog(diego.showGoodbyeMessage(), diegoImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> System.exit(0));
        delay.play();
    }
}
