package park;

import javafx.application.Platform;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Park park;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image parkImage = new Image(this.getClass().getResourceAsStream("/images/park.jpg"));

    @FXML
    public void initialize() {
        String welcomeMessage = """
                Hello! I'm Park
                What can I do for you?""";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getParkDialog(welcomeMessage, parkImage));
    }

    /**
     * Injects the Park instance
     */
    public void setPark(Park p) {
        park = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Park's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        String input = userInput.getText();
        String response = park.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getParkDialog(response, parkImage)
        );
        if (response.equals(goodbyeMessage)) {
            Platform.exit();
        }
        userInput.clear();
    }
}
