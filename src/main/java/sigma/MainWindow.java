package sigma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sigma.command.CommandType;

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

    private Sigma sigma;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image sigmaImage = new Image(this.getClass().getResourceAsStream("/images/Sigma.jpg"));
    private final Font font = new Font("Open Sans", 12);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSigmaWelcome(sigmaImage));
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
    public void setSigma(Sigma s) {
        sigma = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sigma.getResponse(input);
        CommandType commandType = sigma.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSigmaDialog(response, sigmaImage, commandType)
        );
        userInput.clear();
        userInput.setPromptText("Message");
    }
}
