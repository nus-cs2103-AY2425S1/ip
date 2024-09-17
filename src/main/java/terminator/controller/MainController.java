package terminator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import terminator.Terminator;
import terminator.components.DialogBox;

/**
 * The root controller class.
 */
public class MainController {

    private final Terminator terminator;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
        }
        String response = terminator.getResponse(input);

        DialogBox userDb = DialogBox.getUserDialog(input);
        DialogBox terminatorDb = DialogBox.getTerminatorDialog(response);

        dialogContainer.getChildren().addAll(userDb, terminatorDb);
        userDb.prefWidthProperty().bind(dialogContainer.widthProperty());
        terminatorDb.prefWidthProperty().bind(dialogContainer.widthProperty());
        userInput.clear();
    }

    /**
     * No-args constructor to allow JavaFX runtime to instantiate the controller.
     */
    public MainController() {
        this.terminator = new Terminator();
    }

    @FXML
    private void initialize() {
        scrollPane.setVvalue(1.0);
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });
        scrollPane.setBackground(Background.EMPTY);
        dialogContainer.setBackground(Background.EMPTY);
    }
}
