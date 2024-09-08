package toothless.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import toothless.ui.Ui;

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

    private Toothless toothless;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Hiccup.png"));
    private Image toothlessImage = new Image(this.getClass().getResourceAsStream("/images/Toothless.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getToothlessDialog(Ui.welcome(), toothlessImage)
        );
    }

    /** Injects the Toothless instance */
    public void setToothless(Toothless t) {
        toothless = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Toothless's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        boolean isExit = false;
        if (input.equalsIgnoreCase("bye")) {
            isExit = true;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getToothlessDialog(Ui.bye(), toothlessImage)
            );
        } else if (input.equalsIgnoreCase("hi")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getToothlessDialog(Ui.greeting(), toothlessImage)
            );
        } else {
            String response = toothless.getResponse(input.toLowerCase());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getToothlessDialog(response, toothlessImage)
            );
        }
        userInput.clear();
        if (isExit) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}