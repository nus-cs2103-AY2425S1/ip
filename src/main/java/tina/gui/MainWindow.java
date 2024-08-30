package tina.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tina.Tina;

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

    private Tina tina;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setTina(Tina d) {
        tina = d;
        displayGreeting();
    }

    /**
     * Displays a greeting message when the application starts.
     */
    private void displayGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getTinaDialog("Hello! I'm Tina, your friendly chatbot. How can I assist you today?", dukeImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tina's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")){
            Main.exit();
        } else {
            String response = tina.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTinaDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
