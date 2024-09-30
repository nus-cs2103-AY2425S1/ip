package BRMO;

import java.text.ParseException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private BRMO brmo;

    @FXML
    public void initialize() throws InvalidCommandException, ParseException {
        brmo = new BRMO();    
        scrollPane.setFitToWidth(true);
        
        dialogContainer.setPrefWidth(scrollPane.getWidth());
        
        scrollPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            dialogContainer.setPrefWidth(newVal.doubleValue());
        });

        String welcomeMessage = brmo.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getBotDialog(welcomeMessage));
    }

    @FXML
    private void handleUserInput() throws ParseException {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = brmo.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getBotDialog(response)
            );
            userInput.clear();

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