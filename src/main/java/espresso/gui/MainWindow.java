package espresso.gui;
//took some help from @RitulKumarSingh
import espresso.command.InvalidCommandException;
import espresso.Espresso;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.text.ParseException;
import javafx.util.Duration;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogBoxContainer;

    @FXML
    private TextField userInputMessage;

    @FXML
    private Button sendButton;

    private Espresso espresso;

    /**
     * Initializes the controller class when the FXML layout is loaded.
     *
     * @throws InvalidCommandException if a command in Espresso is invalid
     * @throws ParseException if there is an error parsing user input
     */
    @FXML
    public void initialize() throws InvalidCommandException, ParseException {
        espresso = new Espresso();
        scrollPane.setFitToWidth(true);

        dialogBoxContainer.setPrefWidth(scrollPane.getWidth());

        scrollPane.widthProperty().addListener((observable, oldVal, newVal) -> {
            dialogBoxContainer.setPrefWidth(newVal.doubleValue());
        });
    }

    @FXML
    private void handleUserInput() throws ParseException {
        String input = userInputMessage.getText();
        if (!input.isEmpty()) {
            String response = espresso.getResponse(input);
            dialogBoxContainer.getChildren().addAll(
                    DialogBox.createUserDialog(input),
                    DialogBox.createBotDialog(response)
            );
            userInputMessage.clear();

            scrollPane.vvalueProperty().unbind();
            Platform.runLater(() -> scrollPane.setVvalue(1.0));

            if (input.equalsIgnoreCase("bye")) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        }
    }
}
