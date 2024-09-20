package totoro;

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
import totoro.command.CommandType;

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

    private Totoro totoro;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image totoroImage = new Image(this.getClass().getResourceAsStream("/images/DaTotoro.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTotoroWelcome(totoroImage));
        userInput.setPromptText("try 'help'");
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

    /** Injects the Totoro instance */
    public void setTotoro(Totoro t) {
        assert t != null;
        totoro = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Totoro's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = totoro.getResponse(input);
        CommandType commandType = totoro.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTotoroDialog(response, totoroImage)
        );
        userInput.clear();
        userInput.setPromptText("try 'help'");

        if (commandType == CommandType.BYE) {
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}


