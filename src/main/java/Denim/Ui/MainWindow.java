package denim.ui;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import denim.Denim;
import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimFileException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private Alert alert;

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

    public void displayGreetingMessage() {
        DialogBox greetingMessage = DialogBox.getDenimDialog("Hello! I'm Denim! "
                + "What can I do for you?", denimImage);
        dialogContainer.getChildren().add(greetingMessage);
    }

    public void injectDenim(Denim d) {
        denim = d;
    }

    public void handleFileNotFound() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("File Not Found");
        alert.setHeaderText("File Not Found");
        alert.setContentText("File denim.txt does not exist. Create?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            denim.exit();
        }

        try {
            denim.handleFileNotFound();
        } catch (DenimFileException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            denim.exit();
        }
    }

    public void handleDirectoryNotFound() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("File Not Found");
        alert.setHeaderText("File Not Found");
        alert.setContentText("Directory data and File denim.txt does not exist. Create?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            denim.exit();
        }

        try {
            denim.handleDirectoryNotFound();
        } catch (DenimDirectoryException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            denim.exit();
        }
    }
}
