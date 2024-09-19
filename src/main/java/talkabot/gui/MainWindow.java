package talkabot.gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import talkabot.TalkaBot;
import talkabot.Ui;

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

    private TalkaBot talkabot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image talkaBotImage = new Image(this.getClass().getResourceAsStream("/images/DaTalker.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Talk-a-Bot instance */
    public void setDuke(TalkaBot t) {
        talkabot = t;
        dialogContainer.getChildren().addAll(
                DialogBox.greet(Ui.getHello(), talkaBotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Talk-a-Bot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = talkabot.getResponse(input);
        String commandType = talkabot.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTalkaBotDialog(response, talkaBotImage, commandType)
        );
        if (commandType == "ByeCommand") {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            }, 3000);
        }
        userInput.clear();
    }

}

