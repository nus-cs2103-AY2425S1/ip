package twilight;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import java.util.Timer;
//import java.util.TimerTask;

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

    private Twilight twilight;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image twilightImage = new Image(this.getClass().getResourceAsStream("/images/Twilight.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Twilight instance */
    public void setTwilight(Twilight t) {
        twilight = t;
        dialogContainer.getChildren().addAll(
                DialogBox.getTwilightDialog(twilight.greet(), twilightImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = twilight.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTwilightDialog(response, twilightImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
//            new Timer().schedule(new TimerTask() {
//                public void run () { Platform.exit(); }
//            }, 2000);
            Platform.exit();
        }
    }

    //method for using timer and platform exit from:
    //https://stackoverflow.com/questions/21974415/how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem
    //not being used at the moment because platform exit makes gradle not terminate the run
}
