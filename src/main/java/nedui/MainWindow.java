package nedui;
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
import ned.Ned;

import java.util.Objects;

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

    private Ned ned;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    //    private Image NedImage = new Image(this.getClass().getResourceAsStream("/images/Ned_Stark-Sean_Bean.jpg"));
    private Image nedImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Ned_Stark" +
            "-Sean_Bean.jpg")));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setNed(Ned d) {
        this.ned = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getNedDialog(ned.getWelcomeMessage(), nedImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ned.getResponse(input);
        if (response.equals("EXIT MESSAGE")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getNedDialog(ned.getByeMessage(), nedImage)
            );
            exitApplicationWithDelay(2);
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNedDialog(response, nedImage)
        );
        userInput.clear();
    }

    private void exitApplicationWithDelay(double seconds) {
        PauseTransition delayAnimation = new PauseTransition(Duration.seconds(seconds));
        delayAnimation.setOnFinished(event -> Platform.exit());
        delayAnimation.play();
    }
}
