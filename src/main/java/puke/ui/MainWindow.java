package puke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import puke.Puke;

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

    private Puke puke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/man.png"));
    private Image pukeImage = new Image(this.getClass().getResourceAsStream("/images/puke.png"));

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value property
     * to the height property of the dialog container, ensuring the scroll pane scrolls
     * to show the latest dialog.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Puke instance into the controller.
     * This method is called after the FXML file is loaded to set up the controller with
     * an instance of the Puke class.
     *
     * @param p the Puke instance to be injected.
     */
    public void setPuke(Puke p) {
        puke = p;
        dialogContainer.getChildren().addAll(
                DialogBox.getPukeDialog(puke.getGreetingMessage(), pukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Puke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = puke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPukeDialog(response, pukeImage)
        );
        userInput.clear();
    }
}

