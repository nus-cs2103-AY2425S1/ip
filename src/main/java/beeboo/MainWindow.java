package beeboo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the BeeBoo application. It manages user interactions,
 * handles input, and displays dialogues between the user and BeeBoo.
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
    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/User.jpg"));

    private Beeboo beeboo;

    /**
     * Initializes the main window by setting up the scroll pane's behavior
     * and adding a greeting message to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox greeting = DialogBox.getBeeBooDialogue("Hello! I'm BeeBoo\nWhat can I do for you?",
                Beeboo.getBeebooImage());
        dialogContainer.getChildren().addAll(greeting);
    }

    /**
     * Injects the Beeboo instance into the controller.
     *
     * @param b the Beeboo instance to be injected
     */
    public void setBeeboo(Beeboo b) {
        beeboo = b;
        assert beeboo != null : "Beeboo should not be null";
    }

    /**
     * Handles user input by creating two dialog boxes: one with the user's input and another
     * with BeeBoo's response. The user input is then cleared.
     * If the user inputs "bye", the application exits.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = beeboo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBeeBooDialogue(response, Beeboo.getBeebooImage())
        );
        userInput.clear();
        if (input.toLowerCase().trim().equals("bye")) {
            System.exit(0);
        }
    }
}
