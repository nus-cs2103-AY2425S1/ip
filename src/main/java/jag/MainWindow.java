package jag;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

/**
 * Controller for the main GUI
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jag jag;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jagImage = new Image(this.getClass().getResourceAsStream("/images/DaJag.png"));

    @FXML
    public void initialize() {
        String greetings = UiGUI.getGreetings();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getJagDialog(greetings, jagImage));
    }

    /**
     * Setter for Jag instance
     * @param jag Instance of Jag to be set
     */
    public void setJag(Jag jag) {
        this.jag = jag;
    }

    /**
     * Creates two dialog boxes, one for user input and the second for Jag's chatbot response.
     * It also clears the user input after processing it.
     */
    @FXML
    private void handleUserInput() throws AExceptions {
        String input = userInput.getText();
        String response = jag.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJagDialog(response, jagImage)
        );
        userInput.clear();

        // Stop the JavaFX application from running after bye command
        if (response.contains("Bye")) {
            Platform.exit();
        }
    }
}
