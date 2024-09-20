package mryapper.ui;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

import mryapper.MrYapper;
import mryapper.exception.InvalidFileDataException;

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

    private MrYapper mrYapper;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image yapperImage = new Image(this.getClass().getResourceAsStream("/images/MrYapper.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the MrYapper instance */
    public void setYapper(MrYapper y) {
        mrYapper = y;
        try {
            mrYapper.loadData();
            dialogContainer.getChildren().addAll(DialogBox.getYapperDialog(y.greet(), yapperImage));
        } catch (IOException e) {
            forceExit("An error occurred while creating a data file for storage :(\n" + e.getMessage());
        } catch (InvalidFileDataException e) {
            forceExit(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mrYapper.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapperDialog(response, yapperImage)
        );
        
        userInput.clear();
        if (response.equals("Bye. Hope to see you again soon!")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    private void forceExit(String exitMessage) {
        dialogContainer.getChildren().addAll(DialogBox.getYapperDialog(exitMessage, yapperImage));
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}

