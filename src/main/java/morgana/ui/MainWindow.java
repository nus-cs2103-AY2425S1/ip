package morgana.ui;

import static morgana.common.Messages.MESSAGE_WELCOME;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import morgana.Morgana;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private final Morgana morgana;
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/ren.png"));
    private final Image monaImage = new Image(getClass().getResourceAsStream("/images/morgana.png"));

    public MainWindow(Morgana morgana) {
        this.morgana = morgana;
    }

    /**
     * Initializes the main GUI.
     * Binds the scroll pane to the dialog container's height and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getMonaDialog(MESSAGE_WELCOME, monaImage, null));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Morgana's response, and then appends them to the dialog container. If the
     * response is from a {@code ByeCommand}, the application exits after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = morgana.getResponse(input);
        String styleClass = morgana.getStyleClass();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMonaDialog(response, monaImage, styleClass)
        );

        if (response.startsWith("Bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
        userInput.requestFocus();
    }
}
