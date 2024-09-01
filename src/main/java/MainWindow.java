import hana.Hana;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_STYLE = "-fx-background-color: green;"
            + " -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 5;";
    private static final String BOT_STYLE = "-fx-background-color: blue;"
            + "-fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 5;";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hana hana = new Hana();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/default.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/hana.jpg"));

    /**
     * Initialize with greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetings = hana.getResponse("hello");
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(greetings, botImage));
    }

    /** Injects the Duke instance */
    public void setHana(Hana d) {
        hana = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hana.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
