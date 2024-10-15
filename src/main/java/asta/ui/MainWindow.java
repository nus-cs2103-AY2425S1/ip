package asta.ui;

import java.util.Objects;

import asta.Asta;
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
    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;

    private Asta asta;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Asta instance
     */
    public void setAsta(Asta asta) {
        this.asta = asta;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = asta.getResponse(input);
        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
