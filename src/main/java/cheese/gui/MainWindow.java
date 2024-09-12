package cheese.gui;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import cheese.WheelyBigCheese;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for main GUI
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

    private WheelyBigCheese bot;

    private Image userImage = new Image(Objects.requireNonNull(
        this.getClass().getResourceAsStream("/images/person.png")
    ));
    private Image botImage = new Image(Objects.requireNonNull(
        this.getClass().getResourceAsStream("/images/robot.png")
    ));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the CheeseBot instance and adds intro message */
    public void setCheese(WheelyBigCheese b) {
        bot = b;
        String response = bot.start();
        dialogContainer.getChildren().add(
            DialogBox.getBotDialog(response, botImage
        ));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        assert userImage != null;
        assert botImage != null;
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();

        if (bot.isExit()) {
            // If exit, close program after 3s
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> System.exit(0));
                }
            }, 3000);
        }
    }
}
