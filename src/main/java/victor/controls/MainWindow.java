package victor.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import victor.ui.UI;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private UI ui;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/toucan.png"));
    private Image victorImage = new Image(this.getClass().getResourceAsStream("/images/victor.png"));

    @FXML
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects UI instance.
     * @param ui A UI element handling user input and output.
     */
    public void setUi(UI ui) {
        this.ui = ui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVictorDialog(response, victorImage)
        );
        userInput.clear();
    }
}
