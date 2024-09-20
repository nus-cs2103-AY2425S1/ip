package carly.ui;

import carly.Carly;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

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
    @FXML

    private AudioClip sendSound;


    private Carly carly;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/elephanticon.jpg"));
    private final Image carlyImage = new Image(this.getClass().getResourceAsStream("/images/penguinicon.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        startChat();

        userInput.setPromptText("Type here...");

        String soundPath = "/sounds/send_sound.mp3";
        sendSound = new AudioClip(this.getClass().getResource(soundPath).toString());
    }

    /** Injects the Carly instance */
    public void setCarly(Carly c) {
        carly = c;
    }


    private void startChat() {
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getCarlyDialog(ui.welcomeMsg(), carlyImage)
        );
        // Hide the input field and send button at startup
        userInput.setVisible(true);
        sendButton.setVisible(true);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Carly's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        sendSound.play();
        String input = userInput.getText();
        String response = carly.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCarlyDialog(response, carlyImage));
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            System.exit(0);
        }
    }
}
