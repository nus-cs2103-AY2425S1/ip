package noosy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import noosy.Noosy;

/**
 * Controller for the main GUI of the Noosy application.
 * This class manages the interaction between the user interface and the Noosy logic.
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

    /** The main Noosy instance used for processing user inputs and generating responses. */
    private Noosy noosy;

    /** Image used to represent the user in the chat interface. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/yellow teletubby.jpg"));

    /** Image used to represent Noosy in the chat interface. */
    private Image noosyImage = new Image(this.getClass().getResourceAsStream("/images/red teletubby.jpeg"));

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Noosy instance into the controller.
     * This method should be called after the controller is created to set up the Noosy instance.
     *
     * @param n The Noosy instance to be used by this controller.
     */
    public void setNoosy(Noosy n) {
        noosy = n;
        Gui gui = new Gui(dialogContainer);
        noosy.setUi(gui);
    }

    /**
     * Handles user input when the send button is clicked or enter is pressed.
     * Creates two dialog boxes, one echoing user input and the other containing Noosy's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = noosy.getResponse(input);
        String commandType = noosy.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNoosyDialog(response, noosyImage, commandType)
        );
        userInput.clear();
    }
}
