package main;
import javafx.animation.PauseTransition;
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

    private Dash dash;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dashImage = new Image(this.getClass().getResourceAsStream("/images/Dash.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDash(Dash d) {
        this.dash = d;
        String greeting = Ui.displayGreeting();
        dialogContainer.getChildren().add(DialogBox.getDashDialog(greeting, dashImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dash.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDashDialog(response, dashImage)
        );
        //handling exit case
        if (input.equals("bye") || input.equals("b")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
