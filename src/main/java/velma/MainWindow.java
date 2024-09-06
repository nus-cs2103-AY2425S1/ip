package velma;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Velma velma;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/andrew.jpeg"));
    private Image velmaImage = new Image(this.getClass().getResourceAsStream("/images/Velma.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Velma instance */
    public void setVelma(Velma v) {
        velma = v;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = velma.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVelmaDialog(response, velmaImage)
        );
        playSendMessageSound();
        userInput.clear();
    }

    private void playSendMessageSound() {
        String soundPath = this.getClass().getResource("/sounds/fart-01.wav").toString();
        AudioClip sendSound = new AudioClip(soundPath);
        sendSound.play();
    }

}
