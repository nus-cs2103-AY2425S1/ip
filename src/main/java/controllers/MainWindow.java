package controllers;

import java.util.Objects;

import controllers.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import core.Brock;
import task.TaskList;
import utility.Pair;

import javafx.animation.PauseTransition;
import javafx.util.Duration;


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

    private Brock brock;

    private TaskList tasks;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /** Injects the core.Brock instance */
    public void setBrock(Brock b) {
        this.brock = b;
    }

    public void setTasks(TaskList t) {
        this.tasks = t;
    }

    public void showInitialMessage(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBrockDialog(response, dukeImage)
        );
    }

    // Adopted from https://github.com/nus-cs2103-AY2425S1/forum/issues/199
    public void exitProgram() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing core.Brock's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() { // Responds to user input event
        String rawCommand = userInput.getText();
        String processedCommand = this.processCommand(rawCommand);

        Pair<Boolean, String> respondResult = this.brock
                .respondToCommand(processedCommand, this.tasks);
        boolean isExit = respondResult.getFirst();
        String brockResponse = respondResult.getSecond();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(rawCommand, userImage),
                DialogBox.getBrockDialog(brockResponse, dukeImage)
        );
        userInput.clear();
        if (isExit) {
            exitProgram();
        }
    }

    private String processCommand(String rawCommand) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return rawCommand.trim()
                .replaceAll(" +", " ");
    }


}
