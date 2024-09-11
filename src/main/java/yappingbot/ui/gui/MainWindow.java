package yappingbot.ui.gui;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Platform;
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
    private Button sendButton;
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;

    private final InputStream userImageStream =
            this.getClass().getResourceAsStream("/images/DaUser.png");
    private final InputStream ypImageStream =
            this.getClass().getResourceAsStream("/images/DaDuke.png");
    private Image userImage;
    private Image ypImage;

    private volatile UiGui ui;
    private boolean updateOutput = false;
  
    /**
     * Initialises the MainWindow gui nodes.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        assert userImageStream != null : "userImage is null!";
        assert ypImageStream != null : "userImage is null!";
        userImage = new Image(userImageStream);
        ypImage = new Image(ypImageStream);
    }

    /**
     * Sets the UiGui interface for the window.
     *
     * @param ui UiGui interface
     */
    public void setUi(UiGui ui) {
        this.ui = ui;

        // New thread to monitor the OUTPUT stream from the bot,
        // and set the update flag. Platform.runLater signals
        // JavaFX thread to run the function within its context.
        //
        // This is here instead of in initialize because we need
        // the ui context
        new Thread(() -> {
            while (ui.hasOutputLines()) {
                this.updateOutput = true;
                Platform.runLater(this::handleBotOutput);
            }
        }).start();
    }



    /**
     * Pulls any output present from bot and outputs them.
     */
    private void handleBotOutput() {
        // guard clause to prevent unintended loops
        if (!this.updateOutput) {
            return;
        }

        // generate dialog box for robot
        try {
            assert ui.hasOutputLines();
            String response = ui.getNextOutputLine();
            assert response != null;

            DialogBox replyDialogBox = DialogBox.getReplyDialog(response, ypImage);
            dialogContainer.getChildren().add(replyDialogBox);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

        // unset flag so that if JavaFX runs this, the program will not hang.
        this.updateOutput = false;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null;

        if (input.isEmpty()) {
            return;
        }

        ui.pushInputLine(input);
        try {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        userInput.clear();
    }

    /**
     * Disables the input and send button so that User will stop and close.
     */
    public void disableInputs() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
