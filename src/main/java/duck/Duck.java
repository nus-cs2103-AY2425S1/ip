package duck;

import duck.commands.Command;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.parser.Parser;
import duck.storage.Storage;
import duck.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * The Duck class serves as the main entry point for the Duck application.
 * It handles the initialization of the application, manages the task list,
 * and processes user commands.
 */
public class Duck {
    /** Manages the list of tasks. */
    private final TaskList tasks;

    /** Handles the loading and saving of tasks to a file. */
    private Storage storage;

    /** Handles the user interface operations such as displaying messages. */
    private final Ui ui;

    /** The default file path where the task list is stored. */
    private static final String FILE_PATH = "data/duck.txt";


    /**
     * Initializes the Duck application by setting up the user interface,
     * loading tasks from the storage, and displaying startup messages.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duck(String filePath) {
        this.ui = new Ui();
        tasks = new TaskList();
        try {
            ui.showStartUpMessage();
            storage = new Storage(filePath);
            storage.loadTasks(tasks);
            ui.showStartUpCompleteMessage();
        } catch (DuckException e) {
            ui.displayDukeExceptionMessage(e);
        }
    }

    /**
     * Runs the Duck application, which continuously reads and processes user commands
     * until the exit command is given.
     */
    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand();
                Command command = Parser.parse(message);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (DuckException e) {
                ui.displayDukeExceptionMessage(e);
            }
        }
    }

    /**
     * The main method serves as the entry point of the Duck application.
     * It creates a new Duck instance and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        new Duck(FILE_PATH).run();
    }

}
