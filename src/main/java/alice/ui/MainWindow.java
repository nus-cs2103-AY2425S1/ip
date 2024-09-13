package alice.ui;

import alice.Alice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main window of the application where user and Alice dialogs are displayed.
 * This class handles the user interface components and interactions.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;

    private Alice alice;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image aliceImage = new Image(this.getClass().getResourceAsStream("/images/DaAlice.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAlice(Alice a) {
        alice = a;
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(a.getGreeting(), aliceImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alice.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAliceDialog(response, aliceImage)
        );
        userInput.clear();
    }
}
