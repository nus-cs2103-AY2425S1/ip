package chatbot.views;

import chatbot.logic.Bobby;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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
    private Scene scene;

    private Bobby bobby;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private Image botImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBobby(Bobby bobby) {
        this.bobby = bobby;
    }

    /**
     * Creates 2 dialog boxes, one echoing user input and the other containing bot's reply
     * Then, appends them to the dialog container. Clears input after processing user input
     */
    @FXML
    public void handleUserInput() {
        String userCommand = userInput.getText();
        String botText = bobby.getResponse(userCommand);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userCommand, userImage),
                DialogBox.getBotDialog(botText, botImage)
        );
        userInput.clear();
    }
}
