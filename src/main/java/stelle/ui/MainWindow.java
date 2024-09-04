package stelle.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import stelle.Stelle;

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

    private Stelle stelle;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/belle.jpg"));
    private Image stelleImage = new Image(this.getClass().getResourceAsStream("/images/stelle.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Stelle instance
     * @param s The Stelle instance to inject
     */
    public void setStelle(Stelle s) {
        stelle = s;
        // also print the greeting from Stelle
        dialogContainer.getChildren().addAll(
                DialogBox.getStelleDialog(stelle.getGreeting(), stelleImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stelle.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStelleDialog(response, stelleImage)
        );
        userInput.clear();
    }
}
