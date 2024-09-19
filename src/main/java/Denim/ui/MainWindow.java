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
    @FXML

    private Stage helpStage = new Stage();
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
        Command.CommandStatus commandStatus = commandResult.getStatus();

        userInput.clear();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDenimDialog(reply, denimImage, commandStatus));
    }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            loadHelpWindow();
        } catch (IOException e) {
            Alert helpAlert = new Alert(Alert.AlertType.ERROR);
            helpAlert.setTitle("Help Window Unable to Load");
            helpAlert.setHeaderText("Help Window Unable to Load");
            helpAlert.setContentText("Help Window Unable to Load. \nContinue? You will not be able to use "
                    + "the Help Window if you continue.");
            Optional<ButtonType> result = helpAlert.showAndWait();

            if (result.get() == ButtonType.CANCEL) {
                denim.exit();
            }
            helpButton.setDisable(true);
        }
    }

    private void loadHelpWindow() throws IOException {
        FXMLLoader fxmlHelpLoader = new FXMLLoader(getClass().getResource("/view/HelpWindow.fxml"));
        VBox helpWindow = fxmlHelpLoader.load();
        Scene scene = new Scene(helpWindow);
        helpStage.setTitle("Help");
        helpStage.setScene(scene);
        helpStage.setResizable(false);
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
        DialogBox greetingMessage = DialogBox.getDenimDialog("Okaerinasaimase, goshujinsama (ojousama)!",
                denimImage);
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
        Alert fileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        fileAlert.setTitle("File Not Found");
        fileAlert.setHeaderText("File Not Found");
        fileAlert.setContentText("File denim.txt does not exist. Create?");
        Optional<ButtonType> result = fileAlert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            denim.exit();
        }

        try {
            denim.handleFileNotFound();
        } catch (DenimFileException e) {
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            denim.exit();
        }
    }

    /**
     * Alerts the user that the directory containing the file to read data from does not exist,
     * and prompts the user for actions.
     */
    public void handleDirectoryNotFound() {
        Alert directoryAlert = new Alert(Alert.AlertType.CONFIRMATION);
        directoryAlert.setTitle("File Not Found");
        directoryAlert.setHeaderText("File Not Found");
        directoryAlert.setContentText("Directory data and File denim.txt does not exist. Create?");
        Optional<ButtonType> result = directoryAlert.showAndWait();

        if (result.get() == ButtonType.CANCEL) {
            denim.exit();
        }

        try {
            denim.handleDirectoryNotFound();
        } catch (DenimDirectoryException e) {
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
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
     * Shows a stage with helping instructions.
     */
    public void openHelpWindow() {
        helpStage.show();
    }
}
