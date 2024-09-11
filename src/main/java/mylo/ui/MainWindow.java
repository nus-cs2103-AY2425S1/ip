package mylo.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mylo.command.Command;
import mylo.data.InsufficientInfoException;
import mylo.data.NoSuchCommandException;
import mylo.parser.Parser;
import mylo.storage.StorageOperationException;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Controller for the main GUI of the Mylo application.
 * <p></p>
 * <p>
 * The MainWindow class manages user interaction through the graphical interface. It handles
 * user input, manages dialog display for user and system responses, and processes commands entered by the user.
 * </p>
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

    private UiController controller;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image myloImage = new Image(this.getClass().getResourceAsStream("/images/Mylo.jpg"));

    /**
     * Initializes the MainWindow by setting up the scroll pane to auto-scroll as new dialog entries are added.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays the welcome message in the dialog container when the GUI starts.
     *
     * @param welcomeMessage The message to be displayed as a welcome note.
     */
    public void showWelcomeMessage(String welcomeMessage) {
        DialogBox welcomeDialog = DialogBox.getMyloDialog(welcomeMessage, myloImage);
        dialogContainer.getChildren().add(welcomeDialog);
    }

    /**
     * Sets the UiController to manage the interaction between the GUI and the TUI.
     *
     * @param c The UiController instance that controls the logic behind the UI interactions.
     */
    public void setController(UiController c) {
        this.controller = c;
    }

    /**
     * Handles user input when the send button is pressed or when enter is hit in the text field.
     * <p></p>
     * <p>
     * It creates two dialog boxes: one for echoing the user's input and one for displaying the response
     * from the system.
     * If the command is an exit command, the application is terminated.
     * </p>
     */
    @FXML
    private void handleUserInput() {
        String userCommand = userInput.getText();

        DialogBox inputBox = DialogBox.getUserDialog(userCommand, userImage);
        dialogContainer.getChildren().add(inputBox);

        controller.notifyTui(userCommand, true);

        try {
            Command command = Parser.parse(userCommand);
            String response = controller.execute(command);

            DialogBox responseBox = DialogBox.getMyloDialog(response, myloImage);
            dialogContainer.getChildren().add(responseBox);
            controller.notifyTui(response, false);

            if (command.isExit()) {
                Platform.exit();
            }
        } catch (NoSuchCommandException | StorageOperationException | InsufficientInfoException
                 | IllegalValueException | IndexOutOfBoundsException e) {
            String errorMessage = e.getMessage();
            DialogBox errorBox = DialogBox.getMyloDialog(errorMessage, myloImage);
            dialogContainer.getChildren().add(errorBox);
            controller.notifyTui(errorMessage, false);
        } finally {
            userInput.clear();
        }
    }
}
