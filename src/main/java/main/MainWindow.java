package main;

import commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import storage.Storage;
import ui.DialogBox;
import ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * The {@code MainWindow} class is the controller for the main window of the ProYapper chatbot application.
 * It handles user input, processes it, and displays the corresponding response in the UI.
 */
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

    /**
     * Initializes the main window by setting up necessary components like {@code scrollPane}, {@code taskList}, and displaying
     * the welcome message from ProYapper.
     */
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

    /**
     * Sets the {@code ProYapper} instance for this controller and connects it to the dialog container.
     *
     * @param proYapper the ProYapper instance to set
     */
    public void setProYapper(ProYapper proYapper) {
        this.proYapper = proYapper;
        proYapper.setDialogContainer(dialogContainer);
    }

    /**
     * Handles user input, processes the command, and adds the response to the dialog container.
     * Also, checks if the "bye" command is given to terminate the application.
     */
    @FXML
    private void handleUserInput() {
        String input = getUserInput();
        if (isEmptyInput(input)) {
            return;
        }

        displayUserDialog(input);

        String response = processInput(input);

        displayProYapperResponse(response);

        clearUserInput();

        if (isExitCommand(input)) {
            exit();
        }
    }

    /**
     * Retrieves and trims the user's input.
     *
     * @return The trimmed input from the user.
     */
    private String getUserInput() {
        return userInput.getText().trim();
    }

    /**
     * Checks if the input is empty.
     *
     * @param input The input string to check.
     * @return {@code true} if the input is empty, {@code false} otherwise.
     */
    private boolean isEmptyInput(String input) {
        return input.isEmpty();
    }

    /**
     * Adds the user's input dialog to the dialog container.
     *
     * @param input The user's input to be displayed in the dialog.
     */
    private void displayUserDialog(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Adds ProYapper's response dialog to the dialog container if the response is not empty.
     *
     * @param response The response from ProYapper to be displayed in the dialog.
     */
    private void displayProYapperResponse(String response) {
        if (response != null && !response.trim().isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getProYapperDialog(response, proYapperImage));
        }
    }

    /**
     * Clears the user input field after the input is processed.
     */
    private void clearUserInput() {
        userInput.clear();
    }

    /**
     * Checks if the user's input is the "bye" command, signaling an exit request.
     *
     * @param input The user's input string.
     * @return {@code true} if the input is "bye" (ignoring case), {@code false} otherwise.
     */
    private boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Exits the application after a short delay.
     * This method ensures the user sees the goodbye message before the program terminates.
     */
    private void exit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
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

        return ""; // Return an empty string if output is already managed by Ui.
    }
}
