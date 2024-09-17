package dumpling.ui;

import dumpling.Dumpling;
import dumpling.DumplingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * Obtained and adapted from JavaFx tutorial
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

    private Dumpling dumpling;
    private Ui ui;

    // images are screenshots of Microsoft Word's symbol icons
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chef.png"));
    private Image dumplingImage = new Image(this.getClass().getResourceAsStream("/images/dumpling.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Dumpling instance */
    public void setDumpling(Dumpling d) {
        dumpling = d;
        ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDumplingDialog(ui.getWelcomeMessage(), dumplingImage, false)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        boolean isErrorMessage;
        try {
            response = dumpling.getResponse(input);
            isErrorMessage = false;
        } catch (DumplingException e) {
            response = e.getMessage();
            isErrorMessage = true;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDumplingDialog(response, dumplingImage, isErrorMessage)
        );
        userInput.clear();
        // TODO: how to close the window :')
    }
}
