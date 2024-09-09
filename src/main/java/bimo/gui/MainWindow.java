package bimo.gui;

import bimo.Bimo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents a controller for the GUI.
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
    @FXML

    private Bimo bimo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bimoImage = new Image(this.getClass().getResourceAsStream("/images/Bimo.png"));

    /**
     * Initialises the chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Bimo instance and displays dialogbox for introduction.
     * */
    public void setBimo(Bimo bimo) {
        this.bimo = bimo;
        handleIntroduction();
    }

    /**
     * Creates two dialog boxes, one for user input and one for Bimo's response and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bimo.getResponse(input);
        assert userImage != null : "User image must exist";
        assert bimoImage != null : "Bot image must exist";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBimoDialog(response, bimoImage)
        );
        userInput.clear();
    }

    /**
     * Creates a dialogbox with user greeting and list of commands.
     */
    @FXML
    private void handleIntroduction() {
        String introduction = bimo.greetUser();
        dialogContainer.getChildren().addAll(
                DialogBox.getBimoDialog(introduction, bimoImage));
    }
}
