package ui;

import javafx.fxml.FXML;
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

    private KorolevUI duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Korolev.jpg"));

    private static final String QUOTE = "You can do it quickly, but badly, "
            + "or you can do it slowly, but well. After a while, everyone will forget that it was fast, "
            + "but will remember that it was bad. And vice versa.";

    private static final String WELCOME_MSG = "Welcome comrade! I am Sergei Korolev\n";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .addAll(
                        DialogBox.getDukeDialog(WELCOME_MSG, dukeImage),
                        DialogBox.getDukeDialog(QUOTE, dukeImage)
                        );
    }

    /** Injects the Duke instance */
    public void setDuke(KorolevUI d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

