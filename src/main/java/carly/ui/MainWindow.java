package carly.ui;

import carly.Carly;
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

    private Carly carly;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/resources/images/DaUser.png"));
    private Image CarlyImage = new Image(this.getClass().getResourceAsStream("/images/Dacarly.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Carly instance */
    public void setCarly(Carly c) {
        carly = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Carly's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = carly.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCarlyDialog(response, CarlyImage)
        );
        userInput.clear();
    }
}
