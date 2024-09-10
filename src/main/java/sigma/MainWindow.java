package sigma;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sigma.command.CommandType;

/**
 * Controller for the main GUI of the Sigma application.
 * <p>
 * The {@code MainWindow} class is responsible for managing the main user interface,
 * handling user input, and displaying Sigma's responses in the GUI.
 * It initializes the layout and components, and processes interactions between the user
 * and the application.
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

    /**
     * Initializes the layout and components of the main window.
     * <p>
     * This method binds the scroll pane to the dialog container, sets up the user input prompt,
     * and manages the enabling and disabling of the send button based on the user's input.
     */
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

    /**
     * Injects the Sigma instance into the controller.
     *
     * @param s The Sigma instance to be used by this controller.
     */
    public void setSigma(Sigma s) {
        assert s != null;
        sigma = s;
    }

    /**
     * Handles user input and processes the response from Sigma.
     * <p>
     * This method creates dialog boxes for both the user input and Sigma's response,
     * displays them in the dialog container, and clears the user input field afterward.
     * If the command type is {@code CommandType.BYE}, the application will automatically
     * close after a short delay.
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

        if (commandType == CommandType.BYE) {
            sendButton.setDisable(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
