package slothingwaffler;

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

    private SlothingWaffler slothingWaffler;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/sleeper.png"));
    private final Image slothImage = new Image(this.getClass().getResourceAsStream("/images/sloth.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getSlothingWafflerDialog("Hello! I'm the Slothing Waffler!\n" +
                        "Let's stop slothing and get cracking!", slothImage)
        );
    }

    /** Injects the SlothingWaffler instance */
    public void setSlothingWaffler(SlothingWaffler s) {
        slothingWaffler = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing SlothingWaffler's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = slothingWaffler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSlothingWafflerDialog(response, slothImage)
        );
        userInput.clear();

        if (response.equals("See you next time! Remember to get a waffle!")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

}

