package kita;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatArea;
    @FXML
    private TextField inputBox;
    @FXML
    private Button sendBtn;

    private Kita kitaInstance;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image kitaImage = new Image(this.getClass().getResourceAsStream("/images/queen.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatArea.heightProperty());

    }

    /** Injects the Duke instance */
    public void setKita(Kita k) {
        kitaInstance = k;
        chatArea.getChildren().addAll(
                ChatBubble.getDukeDialog(k.getCommandsExecutor().hello(), kitaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = inputBox.getText();
        String output;
        boolean shouldEnd = false;
        try {
            ParserMessage msgReturned = kitaInstance.getOutput(userText);
            output = msgReturned.getMsg();
            shouldEnd = msgReturned.getEnd();
        }
        catch (Exception e) {
            output = e.toString();
        }
        chatArea.getChildren().addAll(
                ChatBubble.getUserDialog(userText, userImage),
                ChatBubble.getDukeDialog(output, kitaImage)
        );
        inputBox.clear();
        if (shouldEnd) {
            System.exit(0);
        }
    }
}