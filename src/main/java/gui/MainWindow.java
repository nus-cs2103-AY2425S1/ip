package gui;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import nixy.Nixy;

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

    private Nixy nixy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image nixyImage = new Image(this.getClass().getResourceAsStream("/images/DaNixy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nixy instance */
    public void setNixy(Nixy n) {
        nixy = n;
        // Set the display method for Nixy to display messages in the GUI
        nixy.setNewDisplay((String[] messages) -> {
            dialogContainer.getChildren().addAll(
                Arrays.stream(messages)
                    .map(message -> DialogBox.getNixyDialog(message, nixyImage))
                    .collect(Collectors.toList())
            );
        });
        nixy.setOnExit(() -> Platform.exit());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nixy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        // Process the input and display the output as per the set display method (to the GUI)
        nixy.processInput(input);
        userInput.clear();
    }
}
