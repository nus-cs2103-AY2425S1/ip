package ponderpika.gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ponderpika.PonderPika;

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

    private PonderPika ponderPika;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/pikabot.png"));

    /**
     * Helps in initializing GUI
     */
    @FXML
    public void initialize() {
        userInput.requestFocus();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "HELLO, I'm Ponder Pika" + "\nIt is a great day to ponder! How may I help you?";
        dialogContainer.getChildren().add(DialogBox.getPikaDialog(greeting, botImage));

        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/images/send.png"))
                .toExternalForm());
        sendButton.setGraphic(imageView);
        sendButton.setContentDisplay(ContentDisplay.RIGHT);
        imageView.fitWidthProperty().bind(sendButton.widthProperty().divide(6));
        imageView.setPreserveRatio(true);
        sendButton.setMaxWidth(Double.MAX_VALUE);
    }

    /** Injects the PonderPika instance */
    public void setPonderPika(PonderPika p) {
        ponderPika = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String pikaText = ponderPika.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPikaDialog(pikaText, botImage)
        );
        userInput.clear();
        if (userText.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> {
                Platform.exit();
            });
            delay.play();
        }
    }
}
