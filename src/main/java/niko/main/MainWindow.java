package niko.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Niko niko;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/images.jpeg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/images2.jpeg")));

    @FXML
    public void initialize() {
        // Bind the ScrollPane's scroll value to the dialog container's height to auto-scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Niko instance for processing user input.
     *
     * @param niko The Niko chatbot instance.
     */
    public void setNiko(Niko niko) {
        this.niko = niko;
        this.niko.setMainWindow(this);  // Ensure Niko has access to MainWindow
    }

    /**
     * Handles the user input by passing it to Niko and displaying the response.
     */
    @FXML
    public void handleUserInput() {
        // Get user input from the text field
        String input = userInput.getText();
        if (!input.trim().isEmpty()) {
            // Pass the input to Niko for processing
            niko.processUserInput(input);
        }
        userInput.clear();  // Clear the input field after processing
    }

    /**
     * Sets and displays the user input and Niko's response in the dialog container.
     *
     * @param userText The user's input text.
     * @param nikoText Niko's response text.
     */
    public void showDialog(String userText, String nikoText) {
        // Add the user dialog and Niko's response to the dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(nikoText, dukeImage)
        );
    }
}
