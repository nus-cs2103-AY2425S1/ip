package espresso.gui;

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
        assert espresso != null : "Espresso instance should be initialized";
        scrollPane.setFitToWidth(true);

        dialogBoxContainer.setPrefWidth(scrollPane.getWidth());

        scrollPane.widthProperty().addListener((observable, oldVal, newVal) -> {
            dialogBoxContainer.setPrefWidth(newVal.doubleValue());
        });
    }
    //Solution below inspired by https://github.com/nus-cs2103-AY2425S1/ip/pull/557 with permission

    @FXML
    private void handleUserInput() throws ParseException {
        String input = userInputMessage.getText();
        assert input != null : "User input should not be null";
        if (!input.isEmpty()) {
            String response = espresso.getResponse(input);
            assert response != null : "Response from espresso should not be null";
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
