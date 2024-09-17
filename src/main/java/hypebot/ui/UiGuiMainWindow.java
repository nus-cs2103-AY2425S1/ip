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

import java.util.Objects;

public class UiGuiMainWindow extends AnchorPane {
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private final Image hypeBotImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/hypebot.png")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private HypeBot hypeBot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the HypeBot instance */
    public void setHypeBot(HypeBot hypeBot) {
        this.hypeBot = hypeBot;
        dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showGreeting().show(), hypeBotImage),
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showLoadingTasks().show(), hypeBotImage)
        );
        if (hypeBot.hasBootingError()) {
            dialogContainer.getChildren().add(
                    UiGuiDialogBox.getHypeBotDialog(hypeBot.getBootingErrorMessage(), hypeBotImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    UiGuiDialogBox.getHypeBotDialog("DONEZO! Let's CRUSH THOSE TASKS TOGETHER!", hypeBotImage)
            );
        }
    }

    private void exit() {
        dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showSavingTasks().show(), hypeBotImage)
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
        if (commandType.equals("ByeCommand")) {
            exit();
        }
    }
}
