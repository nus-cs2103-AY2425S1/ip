package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import screwllum.Screwllum;

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

    private Screwllum screwllum;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/huehue.jpg"));
    private Image screwllumImage = new Image(this.getClass().getResourceAsStream("/images/screwllum.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Screwllum instance */
    public void setScrewllum(Screwllum s) {
        screwllum = s;
    }

    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getScrewllumDialog(screwllum.showWelcome(), screwllumImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = screwllum.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getScrewllumDialog(response, screwllumImage)
        );
        userInput.clear();
    }
}
