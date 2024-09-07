package mummy.ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mummy.command.Command;


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

    private Mummy mummy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image mummyImage = new Image(this.getClass().getResourceAsStream("/images/Karen.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mummy instance */
    public void setMummy(Mummy d) {
        mummy = d;
        dialogContainer.getChildren().add(
                DialogBox.getMummyDialog(mummy.generateWelcomeMessage(), mummyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mummy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mummy.getResponse(input);
        Command.CommandType commandType = mummy.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMummyDialog(response, mummyImage, commandType)
        );
        userInput.clear();

        if (mummy.hasExitCommand()) {
            exitProgram();
        }
    }

    private void exitProgram() {
        CompletableFuture.delayedExecutor(500, TimeUnit.MILLISECONDS)
                .execute(Platform::exit);
    }
}
