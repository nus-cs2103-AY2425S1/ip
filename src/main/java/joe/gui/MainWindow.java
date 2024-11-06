package joe.gui;

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
import joe.main.Joe;

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

    private Joe joe;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Trump.png"));
    private final Image joeImage = new Image(this.getClass().getResourceAsStream("/images/Joe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Joe instance */
    public void setJoe(Joe j) {
        joe = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        String response = joe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoeDialog(input, response, joeImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
        }
    }
}

