package joe.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import joe.controller.Controller;
import joe.parser.Parser;

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

    private Parser parser;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image JOE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/joeIcon.png"));

    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
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
     * Sets the parser for the MainWindow. The parser is used to parse user input.
     *
     * @param parser The parser to be set.
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Joe's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, USER_IMAGE));
        parser.parse(input);
        userInput.clear();
    }

    @FXML
    public void printBotResponse(String response) {
        dialogContainer.getChildren().add(DialogBox.getJoeDialog(response, JOE_IMAGE));
    }
}
