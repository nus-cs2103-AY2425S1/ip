package denim.ui;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import denim.Denim;
import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimFileException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
    @FXML
    private Button helpButton;

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

    /**
     * Displays a simple greeting message.
     */
    public void displayGreetingMessage() {
        DialogBox greetingMessage = DialogBox.getDenimDialog("Hello! I'm Denim! "
                + "What can I do for you?", denimImage);
        dialogContainer.getChildren().add(greetingMessage);
    }

    /**
     * Injects the instance of Denim being used.
     */
    public void injectDenim(Denim d) {
        denim = d;
    }

    /**
     * Alerts the user that the file to read data from does not exist, and prompts the user for actions.
     */
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

    /**
     * Alerts the user that the directory containing the file to read data from does not exist,
     * and prompts the user for actions.
     */
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

    /**
     * Alerts the user that the file containing the data for the application is corrupted,
     * and suggests to create a new file to overwrite the data, while renaming the corrupted file
     * for user to manually locate the errors.
     */
    public void handleFileCorruption() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("File Corrupted");
        alert.setHeaderText("File Corrupted");
        alert.setContentText("File Corrupted. Create new denim.txt? The corrupted file will be renamed and "
                + "placed in the same directory.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            denim.exit();
        }

        try {
            denim.handleFileCorruption();
        } catch (DenimFileException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            denim.exit();
        }
    }


    /**
     * Creates a new stage containing all the commands available to the program, before showing it to the user.
     */
    public void openHelpWindow() {
        Stage helpStage = new Stage();
        helpStage.setResizable(false);

        VBox helpWindow = new Help();

        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(helpWindow, 600, 500));
        helpStage.show();
    }
}
