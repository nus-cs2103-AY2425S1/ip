package david;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

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

    private David david;

    private Image userImage = getImage("/images/user.png");

    private Image davidImage = getImage("/images/David.png");

    public Image getImage(String path) {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            if (inputStream == null) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No user image found.");
        }
        return new Image(inputStream);
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDavid(David d) {
        david = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing David's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = david.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDavidDialog(response, davidImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            exitProgram();
        }
    }

    /**
     * Display a message on launching the application
     */
    public void launchChatBot() {
        String introMessage = david.getStartUpMessage();
        dialogContainer.getChildren().add(
                DialogBox.getDavidDialog(introMessage, davidImage)
        );
    }


    /**
     * Ends the chatbot after a 1-second timer
     */
    private void exitProgram() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.exit(0);
            }
        }, 1000);
    }
}
