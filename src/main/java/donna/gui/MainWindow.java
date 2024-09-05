package donna.gui;

import donna.Donna;
import donna.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI
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

    private Donna donna;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-img.png"));
    private Image donnaImage = new Image(this.getClass().getResourceAsStream("/images/donna-img.png"));

    /**
     * Initialises the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Donna and UI instance.
     */
    public void setDonna(Donna d, Ui u) {
        donna = d;
        ui = u;
        String greeting = ui.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getDonnaDialog(greeting, donnaImage));

    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = donna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDonnaDialog(response, donnaImage));
        userInput.clear();
    }

}

