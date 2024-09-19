import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import fret.Fret;

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

    private Fret fret;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImg.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Fret.png"));
    
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image image = new Image("/images/SideImg.png");
        imageView.setImage(image);
    }

    public void setBot(Fret f) {
        fret = f;
        welcomeUser();
    }

    @FXML
    public void welcomeUser() {
        dialogContainer.getChildren().add(
            DialogBox.getBotDialog(fret.welcomeUser(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isBlank()) {
            String response = fret.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBotDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
