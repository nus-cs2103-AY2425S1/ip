package main;

import commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import storage.Storage;
import ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;


public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image proYapperImage = new Image(this.getClass().getResourceAsStream("/images/ProYapper.png"));
    private ProYapper proYapper;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Initialize Parser, Storage, and TaskList here or in setProYapper
        this.parser = new Parser();
        this.storage = new Storage("./data/ProYapper.txt");
        this.taskList = new TaskList(storage.loadTasks()); // Load tasks from storage

        String welcomeMessage = "Hello! I am Pro Yapper!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getProYapperDialog(welcomeMessage, proYapperImage));
    }

    public void setProYapper(ProYapper proYapper) {
        this.proYapper = proYapper;
        proYapper.setDialogContainer(dialogContainer);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // No action on empty input
        }

        // Add user's dialog to the container
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        // Process user input and get the response
        String response = processInput(input);

        // Add ProYapper's response to the container
        if (response != null && !response.trim().isEmpty()) {
            DialogBox proYapperDialog = DialogBox.getProYapperDialog(response, proYapperImage);
            dialogContainer.getChildren().add(proYapperDialog);
        }

        // Clear user input field
        userInput.clear();

        // Check if the command is to exit
        if (input.trim().equalsIgnoreCase("bye")) {
            String farewellMessage = "Hope to see you again soon!";
            dialogContainer.getChildren().add(DialogBox.getProYapperDialog(farewellMessage, proYapperImage));

            // Close the application after a short delay to display the message
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }


    /**
     * Processes user input by parsing it into a command and executing the command.
     * @param input The user input as a string.
     * @return The output from executing the command.
     */
    private String processInput(String input) {
        // Use a custom Ui instance to capture the response as a string
        Ui ui = new Ui();
        ui.setDialogContainer(dialogContainer);
        Command command = parser.parseCommand(input);

        // Execute the command and capture its output
        command.execute(taskList, ui, storage);

        // Since the Ui now appends output directly to dialogContainer,
        // we could use a method in Ui to get the last message or similar, but here we assume it appends directly.
        return ""; // Return an empty string if output is already managed by Ui.
    }
}
