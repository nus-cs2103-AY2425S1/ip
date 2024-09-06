package meerkatpack;

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

    private Meerkat meerkat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/yilongma.png"));
    private Image meerkatImage = new Image(this.getClass().getResourceAsStream("/images/meerkat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Meerkat instance */
    public void setMeerkat(Meerkat meerkat) {
        this.meerkat = meerkat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing meerkatpack.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = meerkat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMeerkatDialog(response, meerkatImage)
        );
        userInput.clear();
    }
}