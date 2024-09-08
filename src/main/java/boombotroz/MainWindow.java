package boombotroz;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Contains controller aspect of main GUI.
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

    private Boombotroz boombotroz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image boomImage = new Image(this.getClass().getResourceAsStream("/images/Boom.png"));

    /**
     * Intialises chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBoomDialog("Hello! I am Boombotroz! How may I help you?", boomImage)
        );
    }

    /**
     * Injects the Boombotroz instance.
     */
    public void setBoom(Boombotroz b) {
        boombotroz = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Boombotroz's reply.
     * Appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = boombotroz.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBoomDialog(response, boomImage)
        );
        userInput.clear();
    }
}
