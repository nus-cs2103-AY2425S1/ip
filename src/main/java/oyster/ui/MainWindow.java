package oyster.ui;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import oyster.Oyster;
import oyster.ui.components.DialogBox;

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

    private Oyster oyster;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Oyster instance */
    public void setOyster(Oyster o) {
        oyster = o;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Oyster's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String[] response = oyster.readInput(input);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < response.length; i++) {
            stringBuilder.append(response[i]).append("\n");
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input.trim(), userImage),
            DialogBox.getChatbotDialog(stringBuilder.toString(), chatbotImage)
        );

        userInput.clear();
    }
}
