package duck.gui;

import duck.Duck;
import duck.commands.CommandType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Duck duck;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duckImage = new Image(this.getClass().getResourceAsStream("/images/DaDuck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duck instance */
    public void setDuck(Duck d) {
        duck = d;
    }

    /**
     * Displays greeting on startup.
     */
    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDuckDialog(duck.getGreeting(), duckImage));
    }

    /**
     * Loads tasks and displays greeting on startup.
     */
    public void handleStartUp() {
        duck.loadTasks();
        showGreeting();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duck's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuckDialog(response, duckImage));
        userInput.clear();

        if (input.equals(CommandType.BYE.toString())) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
