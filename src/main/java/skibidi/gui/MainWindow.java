package skibidi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import skibidi.main.Skibidi;


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

    private Skibidi skibidi = new Skibidi("src/main/data/skibidi.txt");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image skibidiImage = new Image(this.getClass().getResourceAsStream("/images/skibidi.jpg"));
    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Good day skibidi Ohio sigma! "
                + "I am Skibidi, your personal task manager. What the dog doin'?"
                + " How can I rizz up your day today? " + "\n" + "Here are all your available goon quests:" + "\n";
        String loadMessage = skibidi.loadTasks();
        dialogContainer.getChildren().add(DialogBox.getSkibidiDialog(welcomeMessage
                + "\n" + loadMessage, skibidiImage));
    }

    /** Injects the Skibidi instance */
    public void setSkibidi(Skibidi s) {
        skibidi = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";

        String response = skibidi.getResponse(input);
        assert response != null && !response.trim().isEmpty() : "Response should not be null or empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkibidiDialog(response, skibidiImage)
        );
        userInput.clear();
    }
}

