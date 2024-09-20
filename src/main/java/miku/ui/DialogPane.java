package miku.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import miku.Run;

/**
 * Dialog Pane that shows all the dialogs.
 */
public class DialogPane extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox box;
    @FXML
    private TextField inputField;
    @FXML
    private Button sendButton;

    private AnchorPane anchorPane;

    /**
     * Adds a node (e.g., dialog box) to the VBox inside the scroll pane.
     * @param node The node to be added.
     */
    public void add(Node node) {
        this.box.getChildren().add(node);
    }

    /**
     * Initialises a dialog container.
     */
    @FXML
    public void initialize() {
        box.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }

    /**
     * Handle user input and update the UI
     */
    @FXML
    public void handleUserInput() {
        Run run = new Run();
        String response = run.getResponse(inputField.getText());
        box.getChildren().addAll(
                DialogBox.getUserDialog(inputField.getText(), new Image(getClass().getResourceAsStream("/user.png"))),
                DialogBox.getDukeDialog(response, new Image(getClass().getResourceAsStream("/miku.jpeg")))
        );
        inputField.clear();
    }
}
