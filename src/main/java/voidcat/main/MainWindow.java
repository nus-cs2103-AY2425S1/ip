package voidcat.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import voidcat.ui.Ui;

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

    private VoidCat voidCat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserCat.png"));
    private Image voidCatImage = new Image(this.getClass().getResourceAsStream("/images/VoidCat.png"));

    /**
     * Initialises the Main window with the dialogContainer containing the dialog boxes
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = Ui.welcome();
        dialogContainer.getChildren()
                .add(DialogBox.getVoidCatDialog(welcomeMessage, voidCatImage));
    }

    /**
     * Injects the VoidCat instance and welcomes the user
     * @param v VoidCat instance
     */
    public void setVoidCat(VoidCat v) {
        voidCat = v;
    }

    /**
     * Creates a dialog box that echoes user input and another of VoidCat's response.
     * Clears user input after process is done.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = voidCat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVoidCatDialog(response, voidCatImage)
        );
        userInput.clear();
    }
}
