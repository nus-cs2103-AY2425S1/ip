package gui;

import casper.CasperBot;
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

    private CasperBot casperBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image casperImage = new Image(this.getClass().getResourceAsStream("/images/Casper.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the CasperBot instance */
    public void setCasperBot(CasperBot casperBot) {
        this.casperBot = casperBot;
        String initialGreeting = this.casperBot.initApplication();
        dialogContainer.getChildren().addAll(
                DialogBox.getCasperDialog(initialGreeting, casperImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String output = this.casperBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCasperDialog(output, casperImage)
        );
        userInput.clear();
        if (output.equals("Bye. Hope to see you again soon!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // Delay for 2 seconds
            delay.setOnFinished(event -> Platform.exit()); // Exit the application after the delay
            delay.play(); // Start the delay
        }
    }
}
