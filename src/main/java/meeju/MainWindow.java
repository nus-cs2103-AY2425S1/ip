package meeju;

import javafx.fxml.FXML;
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

    private Meeju meeju;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image meejuImage = new Image(this.getClass().getResourceAsStream("/images/meeju.png"));

    /**
     * Initializes the chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Meow!\n"
                + " Hello! I'm Meeju\n"
                + " What can I do for you?\n\n\n\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getMeejuDialog(welcomeMessage, meejuImage)
        );
    }

    /** Injects the Meeju instance */
    public void setMeeju(Meeju m) {
        meeju = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Meeju's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Upon entering "bye", the program is terminated
     */
    @FXML
    private void handleUserInput() {
        String exitString = "bye";
        String input = userInput.getText();
        String response = meeju.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMeejuDialog(response, meejuImage)
        );
        userInput.clear();
        if (input.equals(exitString)) {
            System.exit(0);
        }
    }
}
