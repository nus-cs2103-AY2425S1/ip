import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luna.Luna;
import luna.LunaException;

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

    private Luna luna;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/User.jpg")));
    private final Image lunaImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Luna.jpg")));

    /** Initialise Luna GUI with dialog */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greetings = "Hello! I'm Luna. What can I do for you?\n"
                + "If you are new or unsure how I can help you, press enter to view a list of commands.";
        dialogContainer.getChildren().addAll(DialogBox.getLunaDialog(greetings, lunaImage));
    }

    /** Injects the Luna instance */
    public void setLuna(Luna luna) {
        this.luna = luna;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        try {
            String response = luna.run(input);

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLunaDialog(response, lunaImage)
            );
        } catch (LunaException e) {
            if (input.isEmpty()) {
                dialogContainer.getChildren().addAll(DialogBox.getLunaErrorDialog(e.getMessage(), lunaImage));
            } else {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getLunaErrorDialog(e.getMessage(), lunaImage)
                );
            }
        } finally {
            userInput.clear();
        }


    }
}
