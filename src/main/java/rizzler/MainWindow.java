package rizzler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

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

    private Rizzler rizzler;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rizzlerImage = new Image(this.getClass().getResourceAsStream("/images/Rizzler.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rizzler instance */
    public void setRizzler(Rizzler r) {
        this.rizzler = r;
        dialogContainer.getChildren().addAll(
                DialogBox.getRizzlerDialog(rizzler.startUp(), rizzlerImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Pair<String, Boolean> response = rizzler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRizzlerDialog(response.getKey(), rizzlerImage)
        );
        userInput.clear();
        if (response.getValue()) {
            System.exit(0);
        }
    }
}
