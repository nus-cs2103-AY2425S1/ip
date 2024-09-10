package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import talker.Talker;

/**
 * Controller for the main GUI
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
    private Talker talker;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image talkerImage = new Image(this.getClass().getResourceAsStream("/images/Talker.jpeg"));

    /**
     * Constructor for a Main Window
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Injects Talker instance
     * @param t Talker chatbot instance
     */
    public void setTalker(Talker t) {
        talker = t;
    }
    /**
     * Creates dialog box containing user input, and appends it
     * to the dialog container. Clears user input after processing
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String talkerText = talker.getResponse(userText);
        String commandType = talker.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getTalkerDialog(talkerText, talkerImage, commandType));
        userInput.clear();
    }

}
