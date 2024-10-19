package com.meow.gui;
import java.io.IOException;

import com.meow.Meow;
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

    private Meow meow;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_avatar.jpg"));
    private Image meowImage = new Image(this.getClass().getResourceAsStream("/images/meow_avatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Meow instance */
    public void setMeow(Meow d) {
        System.out.println("meow gets fucking set lah dickhead");
        meow = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(meow.getWelcomeMessage(), meowImage)
        );     
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * @throws IOException 
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = meow.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, meowImage)
        );
        userInput.clear();
    }
}


