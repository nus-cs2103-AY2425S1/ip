package janet;

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

import java.util.concurrent.TimeUnit;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Janet janet;

    // Images
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/suguru_geto.jpg"));
    private Image janetImage = new Image(this.getClass().getResourceAsStream("/images/janet.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Janet instance */
    public void setJanet(Janet j) {
        janet = j;
        dialogContainer.getChildren().add(
                DialogBox.getJanetDialog(janet.startMessage(), janetImage)
        );  // displays welcome message upon running the GUI
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Janet's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();             // user's input
        String response = janet.getResponse(input);     // response from janet
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJanetDialog(response, janetImage)
        );
        if (response.equals("Bye. Hope to see you again soon!\n")) {
            // @@author ZShunRen-reused
            // Reused from https://github.com/nus-cs2103-AY2425S1/forum/issues/313#issuecomment-2356353182
            // with minor modifications.
            PauseTransition delayAnimation = new PauseTransition(Duration.seconds(2));
            delayAnimation.setOnFinished(event -> Platform.exit());
            delayAnimation.play();
            // @@author
        }
        userInput.clear();
    }
}
