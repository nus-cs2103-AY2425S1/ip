package gale.gui;

import gale.Gale;
import gale.exception.GaleException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Gale gale;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ryangoslingbladerunner.png"));
    private Image galeImage = new Image(this.getClass().getResourceAsStream("/images/gale.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance
     * */
    public void setGale(Gale g) {
        gale = g;
        displayGreeting();
    }

    @FXML
    private void displayGreeting() {
        String greetingMessage = gale.getUi().greet();
        dialogContainer.getChildren().add(
            DialogBox.getGaleDialog(greetingMessage, galeImage)
        );
    }

    /**
     * Displays an error message by Gale in the dialog box.
     * @param errorMessage the error message to be displayed
     */
    public void displayError(String errorMessage) {
        dialogContainer.getChildren().add(
            DialogBox.getGaleDialog(errorMessage, galeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String galeText = gale.getResponse(userText);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getGaleDialog(galeText, galeImage)
        );
        userInput.clear();
        if (userText.equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
        try {
            gale.saveTasks();
        } catch (GaleException e) {
            displayError(e.getMessage());
        }
    }
}
