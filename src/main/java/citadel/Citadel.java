package citadel;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import citadel.Task.TaskList;
import citadel.commands.*;
import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidCommandException;
import citadel.ui.TextUI;

/**
 * The {@code Citadel} class represents the main entry point of the Citadel application.
 * It manages user interactions, processes commands, and coordinates tasks and data storage.
 */
public class Citadel {

    /** The list of tasks managed by the application. */
    private static TaskList items = new TaskList();

    /** The storage system used to persist tasks between sessions. */
    private static final Storage db = new Storage("data/citadel");

    /** The user interface used to interact with the user. */
    private static final TextUI ui = new TextUI();

    /**
     * Loads tasks from the database (storage) into the application.
     */
    public static TaskList loadDatabase() {
        items = db.getTasks();
        assert items != null : "Task list from database cannot be null";
        return items;
    }

    /**
     * Saves tasks from the application into the database (storage).
     */
    public static void saveDatabase() {
        assert items != null : "Task list cannot be null when saving to the database";

        try {
            db.saveData(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input command as a string.
     * @return The response generated based on the input.
     */
    public String getResponse(String input) {
        try {
            return processCommand(input);
        } catch (CitadelException e) {
            return ui.printCitadelException(e);
        } catch (DateTimeParseException e) {
            return ui.printDateTimeParseException();
        } catch (Exception e) {
            return ui.printException(e);
        }
    }

    /**
     * Processes the user's input command and executes the corresponding task.
     *
     * @param input The user's input command as a string.
     * @throws CitadelException If there is an error processing the input.
     */
    public static String processCommand(String input) throws CitadelException {
        assert input != null : "Input string cannot be null";
        assert !input.isEmpty() : "Input string cannot be empty";

        Commands command = Parser.parseCommand(input);
        assert command != null : "Parsed command cannot be null";

        switch (command) {
        case BYE:
            return ui.printGoodbye();
        case LIST:
            return ui.printTasks(items);
        case MARK:
            return new MarkTask(input, items).run();
        case UNMARK:
            return new UnmarkTask(input, items).run();
        case DELETE:
            return new DeleteTask(input, items).run();
        case DEADLINE:
            return new HandleDeadline(input, items).run();
        case EVENT:
            return new HandleEvent(input, items).run();
        case TODO:
            return new HandleTodo(input, items).run();
        case FIND:
            return new FindTask(input, items).run();
        case ADDTAG:
            return new AddTag(input, items).run();
        case REMOVETAG:
            return new RemoveTag(input, items).run();
        case GETTAG:
            return new GetTag(input, items).run();
        default:
            throw new CitadelInvalidCommandException();
        }
    }

    /**
     * Starts the Citadel application by initializing, loading tasks, and processing user commands.
     *
     * @throws IOException If an I/O error occurs during task storage operations.
     */
    public static void main(String[] args) throws IOException {
        initializeApp();
        loadDatabase();
        ui.printTasks(items);
        runApp();
        saveDatabase();
    }

    /**
     * Initializes the application and prints the start message.
     */
    private static void initializeApp() {
        ui.printStart();
    }

    /**
     * Runs the main application loop, processing user commands until the user exits.
     */
    private static void runApp() {
        while (true) {
            try {
                String input = ui.nextLine();
                processCommand(input);

                // Check if the user entered the BYE command to exit
                if (Parser.parseCommand(input) == Commands.BYE) {
                    break;
                }
            } catch (CitadelException e) {
                ui.printCitadelException(e);  // Handle Citadel-specific exceptions
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();  // Handle date parsing errors
            } catch (Exception e) {
                ui.printException(e);  // Handle any other general exceptions
            }
        }
    }
}
