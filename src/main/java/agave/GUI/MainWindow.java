package agave.GUI;

import agave.Agave;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private ScrollPane scrollPane;

    private Agave agave;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image agaveImage = new Image(this.getClass().getResourceAsStream("/images/DaAgave.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAgave(Agave a) {
        agave = a;
    }

    /**
     * Handles the user input when the Send button is clicked or Enter is pressed in the text field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = agave.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, agaveImage)
        );
        userInput.clear();
    }
}
