package peppa.gui;

import peppa.Peppa;
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

    private Peppa peppa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/george.png"));
    private Image peppaImage = new Image(this.getClass().getResourceAsStream("/images/peppa.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Peppa instance */
    public void setPeppa(Peppa d) {
        peppa = d;

        String welcomeMessage = peppa.getUi().showWelcome();
        String reminders = peppa.getUi().showReminders(peppa.getTaskList());
        String initialMessage = welcomeMessage + "\n" + reminders;

        dialogContainer.getChildren().add(
                // show welcome message + upcoming reminders when user enters the chatbot
                DialogBox.getPeppaDialog(initialMessage, peppaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing peppa's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peppa.getResponse(input);

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
                DialogBox.getPeppaDialog(response, peppaImage)
        );
        userInput.clear();
    }
}
