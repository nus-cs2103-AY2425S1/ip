package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tomo.ToMo;

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

    private ToMo tomo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Camille.png"));
    private Image tomoImage = new Image(this.getClass().getResourceAsStream("/images/Gwen.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the ToMo instance */
    public void setToMo(ToMo t) {
        tomo = t;

        dialogContainer.getChildren().addAll(
                DialogBox.getToMoDialog(tomo.getGreeting(), tomoImage),
                DialogBox.getToMoDialog(tomo.getInitializeMessage(), tomoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    public void close() {
        tomo.close();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tomo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getToMoDialog(response, tomoImage)
        );
        userInput.clear();
    }
}
