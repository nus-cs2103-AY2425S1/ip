package Main;

import Commands.Command;
import Data.Storage;
import Data.StoreList;
import Exceptions.InvalidIndexException;
import Parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

public class MainWindow extends AnchorPane{

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Anxiety.png"));
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setFlash(Parser parser, StoreList storeList) {
        this.parser = parser;
        this.storeList = storeList;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
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