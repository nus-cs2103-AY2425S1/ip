package Controller;

import TanjiroBot.Tanjiro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for the main GUI.
 */public class MainWindowController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Tanjiro tanjiro;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nezuko.png"));
    private Image tanjiroImage = new Image(this.getClass().getResourceAsStream("/images/tanjiro.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setTanjiro(Tanjiro t) {
        tanjiro = t;
        greetMessage();
    }

    @FXML
    private void greetMessage(){
        String response = tanjiro.onStartup();
        dialogContainer.getChildren().addAll(
                DialogBoxController.getTanjiroDialog(response,tanjiroImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = tanjiro.run(input);
        dialogContainer.getChildren().addAll(
                DialogBoxController.getUserDialog(input, userImage),
                DialogBoxController.getTanjiroDialog(response, tanjiroImage)
        );
        userInput.clear();
    }
}
