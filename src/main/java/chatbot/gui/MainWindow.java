package chatbot.gui;

import chatbot.Bee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import task.exceptions.InvalidDurationException;

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

    private Bee bee;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image beeImage = new Image(this.getClass().getResourceAsStream("/images/Bee.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bee instance */
    public void setBee(Bee bee) {
        this.bee = bee;
        String welcome = this.bee.welcomeMessage();
        // Default welcome message
        this.dialogContainer.getChildren().add(DialogBox.getBeeDialog(welcome, beeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bee's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.bee.getBeeResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBeeDialog(response, beeImage)
        );

        if (response.equals("Bye~ Hope to see you again soon!")) {
            System.exit(0);
        }
        userInput.clear();
    }
}
