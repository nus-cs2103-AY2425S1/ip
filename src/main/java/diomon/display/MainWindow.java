package diomon.display;

import diomon.Diomon;
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

    private Diomon diomon;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/User.jpg"));
    private Image diomonImage = new Image(getClass().getResourceAsStream("/images/Diomon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getWelcome(diomonImage));
    }

    public void setDuke(Diomon d) {
        diomon = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = diomon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDiomonDialog(response, diomonImage)
        );
        userInput.clear();
    }
}
