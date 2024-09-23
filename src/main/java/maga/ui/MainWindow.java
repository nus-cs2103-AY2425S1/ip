package maga.java;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import maga.Maga;

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

    private Maga maga;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image trumpImage = new Image(this.getClass().getResourceAsStream("/images/Trump.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    /** Injects the Duke instance */
    public void setMaga(Maga m) {
        maga = m;
    }

    private void greet() {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _  __ _ \n"
                + " | |\\/| | / _  | / _   | / _  | \n"
                + " | |  | | ( |_| | ( |_| | ( |_| | \n"
                + " |_|  |_| \\__/  ___| | \\__/  \n"
                + "                  /___/                            \n";

        String greeting = "Hello from\n" + logo + "\nI am THE best chatbot from the one and only"
                + " US of A trust me everyone says I'm the best. How can I help you serve the American people?";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, trumpImage)
        );
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = maga.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, trumpImage)
        );

        if (input.equals("bye")) {
            maga.closeBot();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }

        userInput.clear();
    }

}