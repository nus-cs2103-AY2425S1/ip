package stobberi;

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
import stobberi.components.Ui;

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

    private Stobberi stobberi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Lemon.png"));
    private Image stobberiImage = new Image(this.getClass().getResourceAsStream("/images/Stobberi.png"));

    /** Initialise the application */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getStobberiDialog(Ui.greet(), stobberiImage));
    }

    /** Injects the Duke instance */
    public void setStobberi(Stobberi s) {
        stobberi = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stobberi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStobberiDialog(response, stobberiImage)
        );

        userInput.clear();

        if (input.equals("bye")) {
            delay();
        }
    }

    @FXML
    private void delay() {
        stobberi.saveList();
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        userInput.setDisable(true);
        sendButton.setDisable(true);
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
