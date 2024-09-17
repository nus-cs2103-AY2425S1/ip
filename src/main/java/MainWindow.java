import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mudkip meeks;
    private final String line = "_______________________________________________________\n";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/quack.jpeg"));
    private Image meeksImage = new Image(this.getClass().getResourceAsStream("/images/mudkip.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getMeeksDialog(
                        line + "Hello! I'm Meeks! Your friendly chatbot!\n" +
                                "What can I do for you?\n" , meeksImage)
        );
    }

    /** Injects the Mudkip instance */
    public void setMudkip (Mudkip m) {
        meeks = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String meeksText = meeks.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getMeeksDialog(meeksText, meeksImage)
        );
        userInput.clear();
    }
}
