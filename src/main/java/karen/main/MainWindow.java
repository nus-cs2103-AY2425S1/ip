package karen.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Main window for the GUI
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
    @FXML
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    @FXML
    private Image karenImage = new Image(this.getClass().getResourceAsStream("/images/karen.png"));

    private Karen karen;

    public void setKaren(Karen k) {
        karen = k;
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void handleUserInput() {
        String userText = userInput.getText();
        String karenText = karen.getKarenResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getKarenDialog(karenText, karenImage)
        );
        userInput.clear();
    }
}
