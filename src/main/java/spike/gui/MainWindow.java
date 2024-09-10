package spike.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spike.Spike;

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

    private Spike spike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/colt_icon.png"));
    private Image spikeImage = new Image(this.getClass().getResourceAsStream("/images/spike_icon.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Type here...");
    }

    /** Injects the Spike instance */
    public void setSpike(Spike spike) {
        this.spike = spike;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Spike's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = spike.getResponse(input);
        String commandType = spike.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSpikeDialog(response, spikeImage, commandType)
        );
        userInput.clear();
    }
}
