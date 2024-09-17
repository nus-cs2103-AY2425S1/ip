package fx;

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
import axel.Axel;
import axel.Ui;

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

    private Axel axel;
    protected Ui ui = new Ui();

    protected Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    protected Image axelImage = new Image(this.getClass().getResourceAsStream("/images/Axel.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greet = ui.showWelcomeAsString();
        dialogContainer.getChildren().addAll(
                DialogBox.getAxelDialog(greet, axelImage)
        );
        userInput.clear();
    }

    /**
     * Injects the Axel instance
     */
    public void setAxel(Axel d) {
        axel = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Axel's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = axel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAxelDialog(response, axelImage)
        );
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
