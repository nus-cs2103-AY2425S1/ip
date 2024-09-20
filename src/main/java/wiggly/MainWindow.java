package wiggly;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wiggly.util.Ui;

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

    private Wiggly wiggly;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image wigglyImage = new Image(this.getClass().getResourceAsStream("/images/DaWiggly.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setWiggly(Wiggly w) {
        wiggly = w;
        dialogContainer.getChildren().addAll(
            DialogBox.getWigglyDialog(Ui.GREETING, wigglyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wiggly.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getWigglyDialog(response, wigglyImage)
        );
        userInput.clear();
        if (response.equals(Ui.EXIT)) {
            PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
            pause.setOnFinished(event -> closeWindow());
            pause.play();
        }
    }

    /**
     * Closes the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.close();
    }
}
