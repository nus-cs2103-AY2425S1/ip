package kotori.ui;

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
import kotori.Kotori;

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

    private Kotori kotori;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image kotoriImage = new Image(this.getClass().getResourceAsStream("/images/Kotori.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Kotori instance */
    public void setKotori(Kotori kotori) {
        this.kotori = kotori;
        dialogContainer.getChildren().addAll(
                DialogBox.getKotoriDialog(kotori.getGreeting(), kotoriImage),
                DialogBox.getKotoriDialog(kotori.getReadingStatus(), kotoriImage)
        );
    }

    /**
     * Creates two dialog boxes,one is to illustrate the input of user and the other to show reaction of bot
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kotori.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKotoriDialog(response, kotoriImage)
        );
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                Platform.exit();
            });
            delay.play();
        }
        userInput.clear();
    }
}
