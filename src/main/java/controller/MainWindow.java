package controller;

import java.util.Arrays;

import enums.StatusMessage;
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
        addBotDialog(botManager.loadData().getMessage(), StatusMessage.WELCOME.getMessage());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        addUserDialog(input);

        Response[] responses = botManager.getResponse(input);
        String [] messages = Arrays.stream(responses).map(Response::getMessage).toArray(String[]::new);
        addBotDialog(messages);

        boolean shouldTerminate = Arrays.stream(responses).anyMatch(Response::isTerminating);
        if (shouldTerminate) {
            closeWindow();
        }
    }

    private void addUserDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    private void addBotDialog(String... messages) {
        for (String message : messages) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(message, botManagerImage));
        }
    }

    private void closeWindow() {
        PauseTransition pause = new PauseTransition(Duration.seconds(20));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
