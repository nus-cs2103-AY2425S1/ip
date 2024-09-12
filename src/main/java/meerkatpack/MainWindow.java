package meerkatpack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import taskpack.TaskList;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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

    private Ui ui = new Ui();
    private TaskList taskList = new TaskList();

    private Meerkat meerkat;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/yilongma.png"));
    private final Image meerkatImage = new Image(this.getClass().getResourceAsStream("/images/meerkat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Meerkat instance */
    public void setMeerkat(Meerkat meerkat) {
        this.meerkat = meerkat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Meerkat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = meerkat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMeerkatDialog(response, meerkatImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getMeerkatDialog(ui.showGoodbyeMessage(), meerkatImage)
            );
            TimeUnit.SECONDS.sleep(1);
            Platform.exit();
        }
    }

    @FXML
    private void displayGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getMeerkatDialog(ui.showGreetingMessage(), meerkatImage)
        );
    }

}