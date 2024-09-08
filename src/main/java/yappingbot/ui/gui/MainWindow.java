package yappingbot.ui.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends VBox {
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private UiGui ui;

    public void setUi(UiGui ui) {
        this.ui = ui;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        // String response = duke.getResponse(input);
        try {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage)
              //      DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        userInput.clear();
    }
}
