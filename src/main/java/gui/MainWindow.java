package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wenjigglybot.WenJigglyBot;
import wenjigglybot.Ui;

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

    private WenJigglyBot wenJigglyBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image wenJigglyImage = new Image(this.getClass().getResourceAsStream("/images/wenjigglyBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getWenJigglyDialog(ui.intro(), wenJigglyImage));
    }

    /**
     * Injects the Duke instance
     */
    public void setWenJigglyBot(WenJigglyBot d) {
        wenJigglyBot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // User input
        String input = userInput.getText();
        // Parse user input and process
        String response = wenJigglyBot.parseAndProcess(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWenJigglyDialog(response, wenJigglyImage)
        );
        userInput.clear();
    }
}