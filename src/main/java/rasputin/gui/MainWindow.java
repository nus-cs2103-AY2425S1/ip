package rasputin.gui;

import rasputin.Rasputin;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Rasputin rasputin;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image rasputinImage = new Image(this.getClass().getResourceAsStream("/images/rasputin.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getRasputinDialog(Ui.printGreeting(), rasputinImage)
        );
    }

    /** Injects the Rasputin instance */
    public void setRasputin(Rasputin rasputin) {
        this.rasputin = rasputin;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rasputin's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rasputin.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRasputinDialog(response, rasputinImage)
        );
        userInput.clear();
    }
}