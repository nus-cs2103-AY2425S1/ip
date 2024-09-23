package edith;

import edith.command.Command;
import edith.task.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the main class for the EDITH chatbot application.
 * <p>
 * The Edith class is responsible for initializing and running the chatbot. It handles user interactions,
 * processes commands, and manages the task list. The application uses the classes Ui for user interface operations,
 * Storage for data persistence, TaskList for managing tasks, and Parser for interpreting user commands.
 * </p>
 */
public class Edith {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs an Edith instance with the specified file path for storage.
     * <p>
     * Initializes the user interface, storage, task list, and command parser. Attempts to load the existing task list
     * from the specified file. If an error occurs during loading, it initializes with an empty task list.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Edith(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (EdithException e) {
            System.err.println(e.getMessage() + " Starting with an empty list.");
            tasks = new TaskList();
        } catch (IOException e) {
            System.err.println("An error occurred while loading saved task list. Starting with an empty list.");
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public Ui getUi() {
        return this.ui;
    }

    public String reply(String userInput) {
        StringBuilder response = new StringBuilder();

        try {
            Command command = parser.parse(userInput);
            response.append(command.execute(tasks, ui, storage));
            if (command.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> {
                    Platform.exit();
                });
                delay.play();
            }
        } catch (DateTimeParseException e) {
            response.append(ui.invalidDateTimeError());
        } catch (EdithException e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }
}