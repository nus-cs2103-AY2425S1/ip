package gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import slave.Slave;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static String slaveImageAddress = "/images/slave_pp.jpg";
    private static String userImageAddress = "/images/DaUser.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image slaveImage = new Image(this.getClass().getResourceAsStream(slaveImageAddress));
    private Image userImage = new Image(this.getClass().getResourceAsStream(userImageAddress));
    private Slave slave;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void printWelcomeMsg() {
        for (String string : slave.getWelcomeMsg()) {
            dialogContainer.getChildren().add(DialogBox.getSlaveDialog(string, slaveImage));
        }
    }

    /**
     * Injects the slave.Slave instance
     */
    public void setSlave(Slave s) {
        slave = s;
        assert s != null : "slave object should not be null";
        printWelcomeMsg();
        String[] pastTaskList = slave.getPastTasks();
        if (pastTaskList != null) {
            for (String slaveDialog : pastTaskList) {
                dialogContainer.getChildren().add(DialogBox.getSlaveDialog(slaveDialog, slaveImage));
            }
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing slave.Slave's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            // don't process empty inputs
            return;
        }
        String[] response = slave.getResponse(input);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        for (String msg : response) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getSlaveDialog(msg, slaveImage)
            );
        }

        slave.save();

        userInput.clear();

        // closes the application if user typed "bye"
        if (input.equalsIgnoreCase("bye")) {
            //@@author James_D -reused
            // source:
            // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            // reused the method to close the javafx window after a delay
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            //@@author
        }
    }
}
