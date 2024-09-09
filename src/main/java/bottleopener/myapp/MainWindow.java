package bottleopener.myapp;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the main window of the BottleOpener application.
 *
 * This class manages the user interface of the BottleOpener chatbot application,
 * including handling user input, displaying messages, and managing the state of
 * the application window. It interacts with the `BottleOpener` instance and
 * uses JavaFX components to provide a responsive user interface.
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

    private BottleOpener bot;
    private Stage stage;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/BottleOpener.png"));

    /**
     * Initializes the main window by setting up the scroll pane and adding the initial
     * greeting dialog from the BottleOpener bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBottleOpenerDialog("Hello! I'm BottleOpener!\nWhat can I do for you?\n", botImage)
        );
    }

    /**
     * Sets the `BottleOpener` instance for this controller.
     *
     * @param b The `BottleOpener` instance to be used for handling user input and generating responses.
     */
    public void setBottleOpener(BottleOpener b) {
        bot = b;
    }

    /**
     * Sets the primary stage for this controller.
     *
     * @param stage The primary stage for the application, which can be closed when the bot exits.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles the user input from the text field, processes it through the `BottleOpener` instance,
     * and updates the dialog container with the user's message and the bot's response.
     * If the bot has exited, a delay is introduced before closing the stage.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBottleOpenerDialog(response, botImage)
        );

        if (bot.hasExited()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1)); // 1 second delay
            delay.setOnFinished(event -> stage.close()); // Close the stage after the delay
            delay.play();
        }

        userInput.clear();
    }
}
