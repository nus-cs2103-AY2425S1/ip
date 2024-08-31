package bobbybot.ui;

import bobbybot.BobbyBot;
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

    private BobbyBot bobbyBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Doof.jpg"));
    private final Image bobbyBotImage = new Image(this.getClass().getResourceAsStream("/images/Bobby.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the BobbyBot instance.
     * @param bobbyBot BobbyBot instance.
     */
    public void setBobbyBot(BobbyBot bobbyBot) {
        this.bobbyBot = bobbyBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing BobbyBot's reply.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        bobbyBot.executeInput(input);
        userInput.clear();
    }

    @FXML
    protected void printResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBobbyBotDialog(response, bobbyBotImage)
        );
    }
}
