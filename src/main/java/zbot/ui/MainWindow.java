package zbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zbot.ZBot;

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

    private ZBot zbot;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/default-avatar.jpg"));
    private Image zbotImage = new Image(this.getClass().getResourceAsStream("/images/zbot-dp.jpg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getZBotDialog(ui.intro(), zbotImage));
    }

    /** Injects the ZBot instance */
    public void setZbot(ZBot zbot) {
        this.zbot = zbot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * ZBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.equals("bye")) {
            // Close the window
            System.exit(0);
        }
        String response = zbot.executeInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZBotDialog(response, zbotImage));
        userInput.clear();
    }

    /**
     * Shows the intro message when the chatbot is started.
     */
    @FXML
    public void showIntroMsg() {
        String introMsg = ui.intro();
        dialogContainer.getChildren().add(DialogBox.getZBotDialog(introMsg, zbotImage));
    }
}
