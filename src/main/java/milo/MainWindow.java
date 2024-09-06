package milo;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

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

    private Milo milo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private final Image miloImage = new Image(this.getClass().getResourceAsStream("/images/Milo.jpeg"));

    // Constructor to load FXML and set the root
    public MainWindow() {
        try {
            // Load the FXML file and set the root and controller to this instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Milo instance */
    public void setMilo(Milo m) {
        this.milo = m;
        greetUser();
    }

    /**
     * Greet users
     */
    public void greetUser() {
        String response = this.milo.getResponse("hi");
        dialogContainer.getChildren().addAll(
                DialogBox.getMiloDialog(response, miloImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Milo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = this.milo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMiloDialog(response, miloImage)
        );
        userInput.clear();
        if (input.toLowerCase().strip().equals("bye")) {
            // Exits program
            delayedExit();
        }
    }

    /**
     * Method that exits the program with a delay
     */
    private void delayedExit() {
        // Ensure the dialog is displayed before starting the delay
        PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 3 seconds delay
        delay.setOnFinished(event -> {
            Platform.exit(); // Properly exit the JavaFX application
        });
        delay.play(); // Start the delay timer
    }
}

