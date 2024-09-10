package controllers;

import java.util.Objects;

import core.Brock;
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
import task.TaskList;
import utility.Pair;

/**
 * Controller class for the main GUI.
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
            new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/DaUser.jpg")));
    private final Image brockImage =
            new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/DaBrock.jpg")));

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // Ensures scroll is always to the very end
        this.scrollPane.vvalueProperty()
                .bind(this.dialogContainer.heightProperty());

        // Set text input placeholder
        this.userInput.setPromptText("Enter your command here!");
    }

    /**
     * Injects the {@code Brock} instance.
     *
     * @param b {@code Brock} instance to be injected.
     */
    public void setBrock(Brock b) {
        this.brock = b;
    }

    /**
     * Injects the {@code TaskList} instance.
     *
     * @param t {@code TaskList} instance to be injected.
     */
    public void setTasks(TaskList t) {
        this.tasks = t;
    }

    /**
     * Shows initial Brock response from set up procedure.
     * Which entails creating save file, loading from save file and welcome message.
     *
     * @param response Initial Brock response to be displayed.
     */
    public void showInitialResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBrockDialog(response, brockImage)
        );
    }

    /**
     * Processes the raw command.
     *
     * @param rawCommand Raw command to be processed.
     * @return Processed command.
     */
    private String processCommand(String rawCommand) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return rawCommand.trim()
                .replaceAll(" +", " ");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Brock's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String rawCommand = userInput.getText();
        String processedCommand = this.processCommand(rawCommand);

        Pair<Boolean, String> respondResult = this.brock
                .respondToCommand(processedCommand, this.tasks);
        boolean isExit = respondResult.getFirst();
        String brockResponse = respondResult.getSecond();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(rawCommand, userImage),
                DialogBox.getBrockDialog(brockResponse, brockImage)
        );
        this.userInput.clear();
        if (isExit) {
            this.exitProgram();
        }
    }

    /**
     * Exits the GUI application.
     * Approach was adapted from:
     *      <a href="https://github.com/nus-cs2103-AY2425S1/forum/issues/199#issuecomment-2333192757">...</a>
     */
    public void exitProgram() {
        // Sets a 3-second delay before exiting
        // To make it less abrupt
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
