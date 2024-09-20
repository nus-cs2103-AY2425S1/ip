package eevee.gui;

import eevee.Eevee;
import eevee.EeveeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    public Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Eevee eevee;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.png")));
    private Image eeveeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/eevee.jpeg")));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showGreeting();
    }

    private void showGreeting() {
        String greeting = "Hello! I'm Eevee. How can I help you today?";
        dialogContainer.getChildren().add(DialogBox.getEeveeDialog(greeting, eeveeImage));
    }

    private void showErrorMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getEeveeDialog(message, eeveeImage));
    }

    /** Injects the Duke instance */
    public void setEevee(Eevee e) {
        eevee = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws EeveeException, IOException {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        try {
            String response = eevee.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getEeveeDialog(response, eeveeImage)
            );
        } catch (EeveeException e) {
            showErrorMessage(e.getMessage());
        } catch (IOException e) {
            showErrorMessage("Something went wrong with the input or output. Try again.");
        }
        userInput.clear();
    }
}
