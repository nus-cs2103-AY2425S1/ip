package taskalyn;

import javafx.animation.PauseTransition;
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

    private Taskalyn taskalyn;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image taskalynImage = new Image(this.getClass().getResourceAsStream("/images/taskalyn.png"));

    /**
     * Initializes the scroll pane to automatically scroll to the bottom.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String welcomeMessage = "Hey! I'm Taskalyn :)\n"
                + "Use a valid command to interact with me!";
        dialogContainer.getChildren().add(
                DialogBox.getTaskalynDialog(welcomeMessage, taskalynImage)
        );
    }

    /** Injects the Taskalyn instance */
    public void setTaskalyn(Taskalyn t) {
        taskalyn = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Taskalyn's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    protected void handleUserInput() {
        String input = userInput.getText();
        String response = taskalyn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskalynDialog(response, taskalynImage)
        );
        userInput.clear();
        // Solution below inspired by:
        // https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }
}
