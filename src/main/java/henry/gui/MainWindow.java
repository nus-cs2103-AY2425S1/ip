package henry.gui;

import henry.Henry;
import henry.util.Ui;
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

    private Henry henry;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image henryImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Sets up the chat. */
    @FXML
    public void initialize() {
        ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getHenryDialog(ui.greetings(), henryImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /** Injects the Henry instance. */
    public void setHenry(Henry h) {
        henry = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Henry's reply and then appends them to
     *                 the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = henry.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHenryDialog(response, henryImage)
        );
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
        userInput.clear();
    }
}
