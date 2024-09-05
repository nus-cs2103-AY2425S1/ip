package darwin.gui;

import darwin.Darwin;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/squid.png"));
    private Image darwinImage = new Image(this.getClass().getResourceAsStream("/images/shark.png"));
    private Darwin darwin;

    public void setDarwin(Darwin darwin) {
        this.darwin = darwin;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    @FXML
    private void handleUserInput() {
        String userText = this.userInput.getText();
        String replyText = this.darwin.getResponse(userText);
        String commandType = this.darwin.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBotDialog(replyText, darwinImage, commandType)
        );
        userInput.clear();
    }
}
