package bob.gui;

import bob.Bob;
import bob.data.TaskList;
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

    private Bob bob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/george.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/peppa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bob instance */
    public void setBob(Bob d) {
        bob = d;

        String welcomeMessage = bob.getUi().showWelcome();
        String reminders = bob.getUi().showReminders(bob.getTaskList());
        String initialMessage = welcomeMessage + "\n" + reminders;

        dialogContainer.getChildren().add(
                // show welcome message + upcoming reminders when user enters the chatbot
                DialogBox.getBobDialog(initialMessage, bobImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);

        // Check if the command is "bye" and close the application if it is
        if (input.trim().equalsIgnoreCase("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // 3-second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit(); // Close the application
            }).start();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage)
        );
        userInput.clear();
    }
}
