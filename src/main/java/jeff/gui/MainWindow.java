package jeff.gui;

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
import jeff.Jeff;
import jeff.parser.Parser;

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

    private Jeff jeff;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/person.png"));
    private final Image jeffImage = new Image(this.getClass().getResourceAsStream("/images/jeff.png"));

    /**
     * Initialise the program.
     */
    @FXML
    public void initialize() {
        assert userImage != null : "User image not loaded";
        assert jeffImage != null : "Jeff Chatbot image not loaded";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Sets jeff.
     *
     * @param jeff To be set.
     */
    public void setJeff(Jeff jeff) {
        this.jeff = jeff;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jeff.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJeffDialog(response, jeffImage)
        );
        userInput.clear();

        // Exit the application if the user inputs "bye"
        if (input.equalsIgnoreCase("bye")) {
            // Schedule exit after a short delay
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Shows a welcome message when the application starts.
     */
    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getJeffDialog(
                        Parser.addSpaceInFrontOfEachLine("Hello! I'm Jeff.\nWhat can I do for you?"), jeffImage)
        );
    }
}

