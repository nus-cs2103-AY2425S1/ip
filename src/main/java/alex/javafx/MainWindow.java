package alex.javafx;

import alex.Alex;
import alex.Ui;
import alex.task.Pair;
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

    private Alex alex;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/Alex.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.userInput.setPromptText("Your wish is my command");
    }

    /** Injects the Alex instance*/
    public void setAlex(Alex d) {
        alex = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(ui.showWelcome(), alexImage, "")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Pair<String, String> pair = alex.getResponse(input);
        String response = pair.getFirst();
        String commandType = pair.getSecond();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(response, alexImage, commandType)
        );
        userInput.clear();
    }
}