package controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.BotManager;
import model.Response;

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

    private BotManager botManager;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image botManagerImage = new Image(this.getClass().getResourceAsStream("/images/BotManager.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Setups the BotManager GUI.
     * Injects the BotManager instance and displays a welcome message.
     */
    public void setup() {
        botManager = new BotManager();
        addBotDialog(botManager.loadData().getMessage());
        String welcomeMessage = "Hello, I'm BotManager, your friendly task assistant!\n"
                + "What can I do for you? (Type 'help' to view all available commands)";
        addBotDialog(welcomeMessage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        Response[] responses = botManager.getResponse(input);
        addUserDialog(input);
        for (Response response : responses) {
            addBotDialog(response.getMessage());
        }
        for (Response response : responses) {
            if (response.isTerminating()) {
                closeWindow();
                break;
            }
        }
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    private void addBotDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getBotDialog(message, botManagerImage));
    }

    private void closeWindow() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
