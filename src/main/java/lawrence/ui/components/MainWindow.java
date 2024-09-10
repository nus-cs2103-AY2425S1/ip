package lawrence.ui.components;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lawrence.app.Lawrence;
import lawrence.command.CommandType;

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

    private Lawrence lawrence;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/baller.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/lawrence.jpg"));

    /**
     * Initialises the child components in the main window.
     */
    @FXML
    public void initialize() {
        // add a listener to detect scrolling on the dialogContainer
        dialogContainer.setOnScroll(event -> {
            // get change in scroll direction
            double deltaY = event.getDeltaY();
            double scrollAmount = scrollPane.getVvalue() - deltaY
                    / scrollPane.getContent().getBoundsInLocal().getHeight();

            scrollPane.setVvalue(scrollAmount);
        });
    }

    /**
     * Injects the Bot instance.
     */
    public void setLawrence(Lawrence lawrence) {
        this.lawrence = lawrence;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot reply and then
     * appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lawrence.getResponse(input);
        CommandType type = lawrence.getPreviousCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage, type)
        );

        if (!lawrence.shouldContinue()) {
            Platform.exit();
        }

        userInput.clear();

        // scroll to the bottom after adding new messages
        scrollToBottom();
    }

    /**
     * Sets the vertical value of the scroll pane to emulate scrolling to the bottom of the dialog box.
     */
    private void scrollToBottom() {
        // ensure layout is updated before scrolling
        dialogContainer.layout();
        scrollPane.layout();

        // scroll to the bottom
        scrollPane.setVvalue(1.0);
    }
}
