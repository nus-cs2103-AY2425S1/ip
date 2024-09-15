package tayoo;

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

public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tayoo tayoo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image tayooImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tayoo instance */
    public void setTayoo(Tayoo t) {
        tayoo = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tayoo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tayoo.getResponse(input);
        System.out.println(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, tayooImage)
        );
        userInput.clear();
        if (tayoo.getIsExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3)); // Delay for 3 seconds
            delay.setOnFinished(event -> Platform.exit()); // Exit after delay
            delay.play();
        }
    }

}
