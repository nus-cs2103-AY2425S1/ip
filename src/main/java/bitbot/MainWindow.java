package bitbot;

//@@author SwaminathanViswa -reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
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

    private BitBot bitbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bitbotImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // This is to add the introduction paragraph when the application first starts.
        dialogContainer.getChildren().add(
                DialogBox.getBitBotDialog(UI.intro, bitbotImage)
        );
    }

    /** Injects the BitBot instance */
    public void setBitbot(BitBot d) {
        bitbot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = bitbot.getResponse(input);
        if (input.equals("bye")) {
            // This is to display the conclusion when the user types "bye"
            dialogContainer.getChildren().add(
                    DialogBox.getBitBotDialog(UI.conclusion, bitbotImage)
            );
            //@@author SwaminathanViswa -resued
            //Reused from https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
            // with minor modifications

            // This is to set a pause before closing & terminating the application.
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
            pauseTransition.setOnFinished(event -> Platform.exit());
            pauseTransition.play();
            //@@author
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBitBotDialog(response, bitbotImage)
            );
            userInput.clear();
        }


    }
}
//@@author
