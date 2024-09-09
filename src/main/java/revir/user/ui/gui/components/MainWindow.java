package revir.user.ui.gui.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import revir.user.ui.gui.Gui;

/**
 * Represents the main window of the GUI.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Gui gui;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void sendBotMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(message, dukeImage));
    }

    public void setGuiHandler(Gui gui) {
        this.gui = gui;
    }

    /**
     * Creates a dialog box containing user input, and appends it to the dialog
     * container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String userText = userInput.getText();
        this.dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage));
        userInput.clear();
        this.gui.handleUserInput(userText);
    }

}
