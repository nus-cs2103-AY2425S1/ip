package Majima.gui;

import Majima.Majima;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Majima majimabot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image majimabotImage = new Image(this.getClass().getResourceAsStream("/images/Majimabot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Majimabot instance
     */
    public void setMajimabot(Majima majimabot) {
        this.majimabot = majimabot;

        String greeting = majimabot.getUi().userGreet();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, majimabotImage));
    }

    /**
     * Handles user input by creating dialog boxes for both user input and Majimabot's response.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = majimabot.getResponse(input); // Get response from Majimabot

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, majimabotImage)
        );
        userInput.clear();
    }

}
