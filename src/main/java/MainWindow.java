import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import snowy.Snowy;

public class MainWindow extends AnchorPane {

    private Snowy snowy;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // Images for both human and bot are taken from http://www.freepik.com

    private Image humanImage = new Image(this.getClass().getResourceAsStream("images/human.jpg"));

    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = snowy.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getHumanDialog(input, humanImage),
                DialogBox.getBotDialog(response, botImage));

        userInput.clear();

        if (input.toLowerCase().trim().equals("bye")) {
            Runnable closeTask = Platform::exit;

            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
            executor.schedule(closeTask, 2, TimeUnit.SECONDS);
            executor.shutdown();
        }
    }

    public void setSnowy(Snowy snowy) {
        this.snowy = snowy;

    }

    public void setInitialMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(snowy.getResponse("hello"), botImage));
    }
}
