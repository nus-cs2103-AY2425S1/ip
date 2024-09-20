import carine.ui.Ui;
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

    private Carine carine;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image carineImage = new Image(this.getClass().getResourceAsStream("/images/Carine.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getCarineDialog(Ui.printGreeting(), carineImage),
                DialogBox.getCarineDialog(Ui.printCommand(), carineImage)
        );

        Platform.runLater(() -> {
            String reminderMessage = carine.getTaskList().setReminder();
            if (!reminderMessage.isEmpty()) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getReminderDialog(reminderMessage, carineImage)
                );
            }
        });
    }

    /** Injects the Duke instance */
    public void setCarine(Carine d) {
        carine = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = carine.getResponse(input);
        if (response.equals("exit")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getCarineDialog(Ui.printGoodbye(), carineImage)
            );
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCarineDialog(response, carineImage)
        );
        userInput.clear();
    }
}
