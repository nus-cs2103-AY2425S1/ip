package yapbot.main;

import java.time.format.DateTimeParseException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yapbot.exceptions.YapBotException;

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

    private YapBot yapBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image yapBotImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));
    private Image errorImage = new Image(this.getClass().getResourceAsStream("/images/errorRobot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the YapBot instance */
    public void setYapBot(YapBot yapBot) {
        this.yapBot = yapBot;
    }

    public void printYapBotDialog(String input) {
        dialogContainer.getChildren().add(DialogBox.getYapBotDialog(input, yapBotImage, false));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing YapBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        try {
            String response = yapBot.getResponse(input);
            assert !response.isEmpty(): "No Response from YapBot";

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYapBotDialog(response, yapBotImage, false)
            );
        } catch (YapBotException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYapBotDialog(e.getMessage(), errorImage, true)
            );
        } catch (NumberFormatException e) {
            String message = "Error, Natural Language Processing Module offline...\nSpecify "
                    + "Task number instead (eg. \"1\", \"2\").";

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYapBotDialog(message, errorImage, true)
            );
        } catch (DateTimeParseException e) {
            String message = "Error, Dynamic DateTime Module offline."
                    + "\nDate & Time should be one of these formats:"
                    + "\n  Date & Time - \"5pm 2024/09/01\""
                    + "\n  Date Only (Time defaults to 8am) - \"2024/09/01\""
                    + "\n  Time Only (Date defaults to today) - \"5pm\"";

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYapBotDialog(message, errorImage, true)
            );
        } finally {
            userInput.clear();

            if (yapBot.shouldExit()) {
                Platform.exit();
            }
        }
    }
}
