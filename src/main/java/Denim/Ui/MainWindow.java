package denim.ui;

import java.util.Timer;
import java.util.TimerTask;

import denim.Denim;
import denim.commands.Command;
import denim.commands.CommandResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * A class representing the main container for the other components of the UI.
 */
public class MainWindow extends VBox {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Denim denim;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image denimImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = denim.parseGuiCommand(input);

        if (command.isExit()) {
            handleExit();
        }

        CommandResult commandResult = denim.executeGuiCommand(command);
        String reply = commandResult.getReply();

        userInput.clear();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDenimDialog(reply, denimImage));
    }

    @FXML
    private void initialize() {
        // Bind the vertical scroll value to the height property of the dialog container
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        displayGreetingMessage();
    }

    private void handleExit() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                denim.exit();
            }
        };
        timer.schedule(task, 1000);
    }

    private void displayGreetingMessage() {
        DialogBox greetingMessage = DialogBox.getDenimDialog("Hello! I'm Denim! "
                + "What can I do for you?", denimImage);
        dialogContainer.getChildren().add(greetingMessage);
    }

    public void injectDenim(Denim d) {
        denim = d;
    }
}
