package javafx;

import cook.Cook;
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

    private Cook cook;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private Image cookImage = new Image(this.getClass().getResourceAsStream("/images/cook.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Cook instance */
    public void setCook(Cook c) {
        cook = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cook's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cook.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCookDialog(response, cookImage)
        );
        // Solution below adapted from
        // https://stackoverflow.com/questions/12153622/how-to-close-a-javafx-application-on-window-close
        if (response.equals("Bye.")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
