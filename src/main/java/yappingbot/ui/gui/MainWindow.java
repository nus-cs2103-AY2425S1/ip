package yappingbot.ui.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends VBox {
    @FXML
    private TextField userInput;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image ypImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private UiGui ui;

    public void setUi(UiGui ui) {
        this.ui = ui;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            dialogContainer.getChildren().add(DialogBox.getReplyDialog(input, ypImage));
            // while (ui.hasOutputLines()) {
            //     ui.pushInputLine(input);
            //     String response = ui.getNextOutputLine();
            //     dialogContainer.getChildren().add(DialogBox.getReplyDialog(response, ypImage));
            // }
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        userInput.clear();
    }
}
