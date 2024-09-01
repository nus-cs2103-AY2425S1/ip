package duke.gui;

import java.util.concurrent.BlockingQueue;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private BlockingQueue<String> inputQueue;
    private BlockingQueue<String> outputQueue;

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the input and output queues. */
    public void connect(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.inputQueue.add(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
    }

    @FXML
    private void handleBobOutput(String output) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(output, dukeImage)
        );
    }

    /**
     * Listen for output from the output queue.
     */
    public void listen() {
        new Thread(() -> {
            while (true) {
                try {
                    String output = this.outputQueue.take();
                    Platform.runLater(() -> {
                        handleBobOutput(output);
                    });
                } catch (InterruptedException e) {
                    ;
                }

            }
        }).start();
    }
}
