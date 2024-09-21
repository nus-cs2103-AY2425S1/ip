package nimbus.gui;

import java.io.IOException;

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
import nimbus.Nimbus;
import nimbus.ui.Ui;

/**
 * Controller for the main.css GUI.
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

    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image nimbusImage =
            new Image(this.getClass().getResourceAsStream("/images/nimbus.jpg"));
    private Nimbus nimbus = new Nimbus("nimbus.txt");

    /**
     * Initialises Main Window and shows greet message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getNimbusDialog(Ui.showWelcome(), nimbusImage));
    }

    /**
     * Injects the Nimbus instance
     */
    public void setNimbus(Nimbus n) {
        nimbus = n;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nimbus's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = nimbus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNimbusDialog(response, nimbusImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {
                Platform.exit();
            });
            delay.play();
        }
    }
}
