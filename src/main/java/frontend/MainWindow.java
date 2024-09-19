package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.Ui;
import java.util.concurrent.TimeUnit;

import alpha.Alpha;
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
    
    private Alpha alpha;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image alphaImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    /** Injects the Alpha instance */
    public void setAlpha(Alpha d) {
        alpha = d;
        Ui ui = new Ui();
        
        String welcomeMessage = ui.welcomeMessage();
        String reminderMessages = d.taskReminder();
        
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, alphaImage),
                DialogBox.getDukeDialog(reminderMessages, alphaImage)
        );
    }
    
    
    @FXML
    public void initialize() {
        // Bind the vertical value of the scrollPane to the height of the dialogContainer
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alpha.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, alphaImage)
        );
        userInput.clear();
    }
}
