package mediell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Mediell mediell;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image mediellNormal = new Image(this.getClass().getResourceAsStream("/images/MediellNormal.png"));
    private Image mediellHappy = new Image(this.getClass().getResourceAsStream("/images/MediellHappy.png"));
    private Image mediellNight = new Image(this.getClass().getResourceAsStream("/images/MediellNight.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(Mediell mediell) {
        this.mediell = mediell;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mediell.getResponse(input);
        Image responseImage = getImage(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, responseImage)
        );
        userInput.clear();
    }

    private Image getImage(String input) {
        switch (mediell.getImage(input)) {
            case DAY:
                return mediellNormal;
            case NIGHT:
                return mediellNight;
            case HAPPY:
                return mediellHappy;
            default:
                return mediellNormal;
        }
    }
}
