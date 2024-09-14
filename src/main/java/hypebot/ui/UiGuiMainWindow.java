package hypebot.ui;

import hypebot.main.HypeBot;
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

public class UiGuiMainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private HypeBot hypeBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image hypeBotImage = new Image(this.getClass().getResourceAsStream("/images/hypebot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the HypeBot instance */
    public void setHypeBot(HypeBot hypeBot) {
        this.hypeBot = hypeBot;
        dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showLoadingTasks(), hypeBotImage),
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showGreeting(), hypeBotImage)
        );
    }

    private void exit() {
        dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showSavingTasks(), hypeBotImage)
        );
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing HypeBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hypeBot.getResponse(input);
        if (response.isEmpty()) {
            return;
        }
        String commandType = hypeBot.getCommandType();
                dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getUserDialog(input, userImage),
                UiGuiDialogBox.getHypeBotDialog(response, hypeBotImage, commandType)
        );
        userInput.clear();
        if (response.equals(hypeBot.getUiCli().showExit())) {
            exit();
        }
    }
}
