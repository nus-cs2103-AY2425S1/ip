package sage;

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
import sage.command.CommandType;

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

    private Sage sage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image sageImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSageWelcome(sageImage));
        userInput.setPromptText("Message");
        sendButton.setDisable(true);
        sendButton.getStyleClass().add("disabled");
        userInput.setFocusTraversable(false);

        userInput.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.trim().isEmpty()) {
                sendButton.setDisable(true);
                sendButton.getStyleClass().add("disabled");
            } else {
                sendButton.setDisable(false);
                sendButton.getStyleClass().remove("disabled");
            }
        });
    }

    /** Injects the Duke instance */
    public void setSage(Sage s) {
        assert s != null;
        sage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sage.getResponse(input);
        CommandType commandType = sage.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSageDialog(response, sageImage)
        );
        userInput.clear();
        userInput.setPromptText("Message");

        if (commandType == CommandType.BYE) {
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}


