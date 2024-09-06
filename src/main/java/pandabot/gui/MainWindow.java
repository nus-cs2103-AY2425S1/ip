package pandabot.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pandabot.main.PandaBot;
import pandabot.ui.Ui;

/**
 * Controller for the main GUI. This class handles the interaction between the
 * user and the PandaBot through a graphical user interface (GUI). It manages
 * user input, displays PandaBot's responses, and handles the exit operation when
 * the user terminates the conversation.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    /**
     * The instance of PandaBot that handles the logic for task management.
     */
    private PandaBot pandaBot;

    /**
     * Image representing the user in the GUI.
     */
    private final Image userImage = new Image("/images/DaUser.png");
    /**
     * Image representing PandaBot in the GUI.
     */
    private final Image dukeImage = new Image("/images/DaDuke.png");

    /**
     * Initializes the main window components.
     * Binds the scroll pane's scroll value to the height of the dialog container so
     * that the scroll pane automatically scrolls as new dialogs are added.
     * Also displays the welcome message from PandaBot when the GUI is initialized.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage));
    }

    /**
     * Injects the PandaBot instance into this controller.
     *
     * @param p The PandaBot instance that handles user commands and responses.
     */
    public void setPandaBot(PandaBot p) {
        pandaBot = p;
    }

    /**
     * Handles user input when the send button is clicked or the user presses enter.
     * It creates two dialog boxes: one that displays the user's input and another
     * that shows PandaBot's response. The user input is then cleared after being processed.
     * If PandaBot's response is a goodbye message ("Bye! Hope to see you again soon!"),
     * the application waits for 3 seconds before closing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pandaBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals("Bye! Hope to see you again soon!")) {
            PauseTransition exitDelay = new PauseTransition(Duration.seconds(3));
            exitDelay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            exitDelay.play();
        }
    }
}
