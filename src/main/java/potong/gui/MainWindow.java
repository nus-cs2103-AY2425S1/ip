package potong.gui;

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
import potong.Potong;
import potong.command.ExitCommand;

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

    private Potong potong;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        firstLaunch();
    }

    /** Injects the Potong instance */
    public void setPotong(Potong p) {
        potong = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = potong.getResponse(input);
        String commandType = potong.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPotongDialog(response, dukeImage, commandType)
        );
        userInput.clear();
        if (commandType.equals("ExitCommand")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(exit -> Platform.exit());
            pause.play();
        }
    }

    @FXML
    private void firstLaunch() {
        dialogContainer.getChildren().add(DialogBox.getIntroDialog("Hello I'm Potong\n What can I do for you?",
                dukeImage));
    }
}
