package barcus.display;

import barcus.Barcus;
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

    private Barcus barcus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userCat.png"));
    private Image barcusImage = new Image(this.getClass().getResourceAsStream("/images/barcusCat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBarcus(Barcus barcus) {
        this.barcus = barcus;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(barcus.getWelcome(), barcusImage, "WelcomeCommand")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = barcus.getResponse(input);
        String commandType = barcus.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, barcusImage, commandType)
        );
        userInput.clear();

        if (commandType.equals("ExitCommand")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}
