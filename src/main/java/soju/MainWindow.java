package soju;

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

    private Soju soju;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/fried-chicken.png"));
    private Image sojuImage = new Image(this.getClass().getResourceAsStream("/images/soju.jpg"));

    /**
     * Initialises the main window of the app and sends a greeting message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = "HI! I LOVE TO DRINK SOJU!!!";
        dialogContainer.getChildren().addAll(
                DialogBox.getSojuDialog(greetingMessage, sojuImage)
        );
    }

    /** Injects the Duke instance */
    public void setSoju(Soju s) {
        soju = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = soju.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSojuDialog(response, sojuImage)
            );
        } catch (SojuException e) {
            System.out.println(e);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(e, sojuImage)
            );
        } finally {
            userInput.clear();
        }
    }
}


