package tissue.window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tissue.Tissue;

/** Controller for the main GUI. */
public class MainWindow {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Tissue tissue;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image tissueImage =
            new Image(this.getClass().getResourceAsStream("/images/tissue.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tissue instance */
    public void setTissue(Tissue t) {
        tissue = t;
        dialogContainer
                .getChildren()
                .addAll(DialogBox.getTissueDialog(tissue.getGreeting(), tissueImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tissue.getResponse(input);
        dialogContainer
                .getChildren()
                .addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getTissueDialog(response, tissueImage));
        userInput.clear();
    }
}
