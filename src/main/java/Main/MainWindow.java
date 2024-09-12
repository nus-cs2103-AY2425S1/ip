package Main;

import Commands.Command;
import Data.Storage;
import Data.StoreList;
import Exceptions.InvalidIndexException;
import Parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

/**
 * The MainWindow class is responsible for managing the main user interface of the application.
 * It handles user interactions, processes commands, and updates the dialog container with responses.
 * It integrates the user interface with the parser and storage systems for managing tasks.
 */
public class MainWindow extends AnchorPane{

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Joy.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML

    private Parser parser;

    private StoreList storeList;

    private String commandType;

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value to
     * the height property of the dialog container and setting up the send button's
     * graphic with an image.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ImageView imageView = new ImageView(getClass().getResource("/images/MemoryBall.png").toExternalForm());
        sendButton.setGraphic(imageView);
        sendButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        imageView.fitWidthProperty().bind(sendButton.widthProperty().divide(2));
        imageView.setPreserveRatio(true);
        //Important otherwise button will wrap to text + graphic size (no resizing on scaling).
        sendButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Injects the parser and the store list into the MainWindow.
     * These will be used to process user commands and manage task data.
     *
     * @param parser The parser responsible for interpreting user input.
     * @param storeList The list of tasks to be managed.
     */
    public void setFlash(Parser parser, StoreList storeList) {
        this.parser = parser;
        this.storeList = storeList;
    }

    /**
     * Handles the user's input by parsing the command, executing it, and displaying
     * both the user's input and the application's response in the dialog container.
     * Also saves the updated task list to the storage file.
     *
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        Command flashCommand = parser.makeSenseOfUserInput(userInput.getText());
        String flashText;
        try {
            flashCommand.setData(storeList);
            flashText = flashCommand.execute();
            commandType = flashCommand.getClass().getSimpleName();
            Storage.saveTasksToFile(storeList.getItems());
        } catch (InvalidIndexException e) {
            flashText = e.getMessage();
        }

        // Display both user input and the command output in the dialog box
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(flashText, dukeImage, commandType)
        );

        userInput.clear();
    }

}