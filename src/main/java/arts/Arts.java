package arts;

import arts.util.Parser;
import arts.util.Storage;
import arts.util.Ui;
import arts.task.TaskList;
import arts.enums.CommandType;
import arts.command.AddDeadlineCommand;
import arts.command.AddEventCommand;
import arts.command.MarkCommand;
import arts.command.UnmarkCommand;
import arts.command.DeleteCommand;
import arts.command.AddTodoCommand;
import arts.command.FindCommand;

import java.time.format.DateTimeFormatter;

/**
 * Main class for the Arts application, responsible for running the task management system.
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
     * Constructs an Arts object with a specified file path for storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Arts(String filePath) {
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
     * Runs the Arts application, processing user commands in a loop until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                CommandType command = parser.parseCommand(input);
                String[] parts = parser.parseArguments(input);

                switch (command) {
                    case BYE:
                        ui.showGoodbye();
                        isExit = true;
                        break;
                    case LIST:
                        ui.showMessage(listTasks());
                        break;
                    case MARK:
                        new MarkCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case UNMARK:
                        new UnmarkCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case DELETE:
                        new DeleteCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case TODO:
                        new AddTodoCommand(tasks, storage, ui, parts[1]).execute();
                        break;
                    case DEADLINE:
                        new AddDeadlineCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
                        break;
                    case EVENT:
                        new AddEventCommand(tasks, storage, ui, parts[1], INPUT_FORMATTERS).execute();
                        break;
                    case FIND:
                        new FindCommand(tasks, ui, parts[1]).execute();
                        break;
                    default:
                        throw new ArtsException("I'm sorry, but I don't know what that means.");
                }
            } catch (ArtsException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
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
}
