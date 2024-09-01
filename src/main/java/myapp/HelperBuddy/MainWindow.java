package myapp.helperbuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow class which helps to create the visuals for the GUI
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

    private Ui buddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image buddyImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initializes the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the buddy from the Ui class.
     */
    public void setBuddy(Ui buddy) {
        this.buddy = buddy;
    }

    /**
     * Handles user input and processes it using the Ui class.
     * Displays the user input and response in the dialog container.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuddyDialog(response, buddyImage)
        );
        userInput.clear();
    }

    /**
     * Handles user input and processes it using the Ui class.
     *
     * @return the buddy response in the dialog container.
     */
    private String getResponse(String input) {
        return buddy.processCommand(input);
    }
}
