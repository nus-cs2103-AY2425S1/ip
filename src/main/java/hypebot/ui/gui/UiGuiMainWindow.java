package hypebot.ui.gui;

import java.util.Objects;

import hypebot.command.ByeCommand;
import hypebot.main.HypeBot;
import hypebot.ui.cli.UiCli;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Represents the main window in the JavaFX {@link Application} for HypeBot which
 * serves as the GUI for the application.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiGuiMainWindow extends AnchorPane {
    /** User profile pic. */
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));

    /** HypeBot profile pic. */
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

    /**
     * Shows the exit message from the {@link HypeBot}'s {@link UiCli}, disables user input,
     * then quits the application after 3 seconds.
     */
    private void exit() {
        dialogContainer.getChildren().addAll(
                UiGuiDialogBox.getHypeBotDialog(hypeBot.getUiCli().showExit().show(), hypeBotImage)
        );
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Creates two {@link UiGuiDialogBox}es, one echoing user input and the other
     * containing HypeBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * <p>When the {@link ByeCommand} is parsed and executed, triggers {@code exit()}.</p>
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
