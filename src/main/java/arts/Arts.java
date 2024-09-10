package arts;

import java.time.format.DateTimeFormatter;

import arts.command.AddDeadlineCommand;
import arts.command.AddEventCommand;
import arts.command.AddTodoCommand;
import arts.command.DeleteCommand;
import arts.command.FindCommand;
import arts.command.MarkCommand;
import arts.command.UnmarkCommand;
import arts.enums.CommandType;
import arts.task.TaskList;
import arts.util.Parser;
import arts.util.Storage;
import arts.util.Ui;

/**
 * The Arts class is the main entry point for the Arts application.
 * It manages the initialization and execution of the application, handling user commands
 * and coordinating between different components like storage, UI, and task management.
 */
public class Arts {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs an Arts object with the specified file path for task storage.
     * Initializes the UI, storage, parser, and loads existing tasks.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Arts(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (ArtsException e) {
            ui.showError(e.getMessage());
            tempTasks = new TaskList();
        }
        tasks = tempTasks;
    }

    /**
     * Generates a response based on user input.
     *
     * @param input The user input.
     * @return A response string.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        try {
            CommandType command = parser.parseCommand(input);
            String[] parts = parser.parseArguments(input);

            switch (command) {
            case BYE:
                return "Bye! Hope to see you again soon!";
            case LIST:
                return listTasks();
            case MARK:
                assert parts.length > 1 : "MARK command requires additional arguments";
                return new MarkCommand(tasks, storage, ui, parts[1]).execute();
            case UNMARK:
                assert parts.length > 1 : "UNMARK command requires additional arguments";
                return new UnmarkCommand(tasks, storage, ui, parts[1]).execute();
            case DELETE:
                assert parts.length > 1 : "DELETE command requires additional arguments";
                return new DeleteCommand(tasks, storage, ui, parts[1]).execute();
            case TODO:
                assert parts.length > 1 : "TODO command requires additional arguments";
                return new AddTodoCommand(tasks, storage, ui, parts[1]).execute();
            case DEADLINE:
                assert parts.length > 1 : "DEADLINE command requires additional arguments";
                return new AddDeadlineCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
            case EVENT:
                assert parts.length > 1 : "EVENT command requires additional arguments";
                return new AddEventCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
            case FIND:
                assert parts.length > 1 : "FIND command requires additional arguments";
                return new FindCommand(tasks, parts[1]).execute();
            default:
                throw new ArtsException("I'm sorry, but I don't know what that means.");
            }
        } catch (ArtsException e) {
            return "OOPS!!! " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @return A string representation of all tasks.
     */
    private String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks yet! Why not add some?";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Main method to start the Arts application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Arts("./data/tasks.txt").run();
    }

    /**
     * Runs the Arts application, processing user commands in a loop until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                assert input != null : "Input from UI cannot be null";
                String response = getResponse(input);
                ui.showMessage(response);
                if (input.equalsIgnoreCase("bye")) {
                    isExit = true;
                }
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
