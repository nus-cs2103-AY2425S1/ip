package nugget.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import nugget.Nugget;

public class ChatUIController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox messageArea;
    @FXML
    private TextField inputField;
    @FXML
    private Button submitButton;

    private Nugget nugget;
    private Image userImage;
    private Image botImage;

    @FXML
    public void initialize() {
        userImage = new Image(getClass().getResourceAsStream("/images/nugget.jpeg"));
        botImage = new Image(getClass().getResourceAsStream("/images/bot.jpeg"));
        messageArea.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    public void setNugget(Nugget nugget) {
        this.nugget = nugget;
    }

    @FXML
    private void handleInput() {
        String input = inputField.getText();
        if (!input.trim().isEmpty()) {
            addMessage(input, true);
            nugget.handleInput(input);
            inputField.clear();
            inputField.requestFocus();
        }
    }

    public void addMessage(String message, boolean isUser) {
        messageArea.getChildren().add(MessageHandler.createMessageBox(message, isUser, userImage, botImage));
    }

    // This method is called by the Ui class to update output in the GUI
    public void updateOutput(String message) {
        addMessage(message, false);
    }
}
