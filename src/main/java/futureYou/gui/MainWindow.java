package futureyou.gui;

import java.util.Objects;

import futureyou.FutureYou;
import futureyou.Ui;
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
    private final Ui ui = new Ui();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private FutureYou futureYou;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image futureYouImage = new Image(this.getClass().getResourceAsStream("/images/futureYou.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String initializationMessage = ui.hello();

        dialogContainer.getChildren().add(DialogBox.getFutureYouDialog(initializationMessage, futureYouImage));
    }

    /**
     * Injects the Future You instance
     */
    public void setFutureYou(FutureYou futureYou) {
        this.futureYou = futureYou;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Future You's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = futureYou.procressResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFutureYouDialog(response, futureYouImage)
        );
        userInput.clear();

        if (Objects.equals(input, "bye")) {

            //@@author James_D
            //Reused from https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            //with minor modifications
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
