package victor.controls;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import victor.Handler;
import victor.commands.Command;
import victor.messages.ReturnMessage;

/**
 * Controller for the main GUI.
 */
public class MainWindow {
    private static final String WELCOME_MESSAGE = "  ~  Hi, I'm Victor! Let's get started with tracking your tasks!";
    private static final Path DATA_PATH = Paths.get("data", "data.txt");
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private Handler handler;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/toucan.png"));
    private Image victorImage = new Image(this.getClass().getResourceAsStream("/images/victor.png"));

    /**
     * Sets Stage instance used by main.
     * @param stage A Stage object used to run the application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("Victor TaskBot");
    }

    /**
     * Initialises a handler for the main window which handles user input.
     */
    public void setHandler() {
        this.handler = new Handler();
    }

    /**
     * Sets a listener for the height of the dialog container to scroll the bottom when
     * text goes beyond bottom of window.
     */
    public void setScrollListener() {
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates an initial welcome message for the user upon starting the program.
     */
    public void welcomeUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getVictorDialog(WELCOME_MESSAGE, victorImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = handler.handleRequest(input);
        if (command.isExit()) {
            stage.close();
        }
        String response = getCommandOutput(command);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVictorDialog(response, victorImage)
        );
        userInput.clear();
    }

    /**
     * Executes, writes command to file, and gets response from command execution.
     * @param command A Command that needs to be executed, written, and made to give a return message.
     * @return A string of the return message from executing the command.
     */
    private String getCommandOutput(Command command) {
        ReturnMessage returnMessage = command.execute();
        command.write(DATA_PATH);
        return returnMessage.getMessagesAsString();
    }
}
