package rei;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Rei rei;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/niki.jpg"));
    private Image reiImage = new Image(this.getClass().getResourceAsStream("/images/rei.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the REI instance */
    public void setRei(Rei r) {
        rei = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing REI's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rei.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getReiDialog(response, reiImage)
        );
        userInput.clear();
        if (input.equals("annyeong")) {
            Platform.exit();
        }
    }
}