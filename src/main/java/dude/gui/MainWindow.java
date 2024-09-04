package dude.gui;

import dude.Dude;
import javafx.application.Platform;
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

    private Dude dude;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Guy.png"));
    private Image dudeImage = new Image(this.getClass().getResourceAsStream("/images/Dude.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Dude instance
     */
    public void setDude(Dude d) {
        dude = d;
        showGreet();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dude's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dude.readAndReact(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDudeDialog(response, dudeImage)
        );

        userInput.clear();
        exitIfNotRunning();
    }

    /**
     * Check if dude is no longer running, and if so, close the application after 3 seconds
     */
    public void exitIfNotRunning() {
        if (!dude.isRunning()) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000); // wait for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            });
        }
    }

    public void showGreet() {
        dialogContainer.getChildren().add(DialogBox.getDudeDialog(dude.greet(), dudeImage));
    }
}
