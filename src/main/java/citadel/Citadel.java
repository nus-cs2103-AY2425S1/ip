package citadel;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import citadel.Task.TaskList;
import citadel.commands.Commands;
import citadel.commands.DeleteTask;
import citadel.commands.FindTask;
import citadel.commands.HandleDeadline;
import citadel.commands.HandleEvent;
import citadel.commands.HandleTodo;
import citadel.commands.MarkTask;
import citadel.commands.UnmarkTask;
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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return handleInput(input);
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
    public static String handleInput(String input) throws CitadelException {
        Commands command = Parser.parseCommand(input);

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

        default:
            throw new CitadelInvalidCommandException();
        }

    }

    /**
     * The main method that starts the Citadel application.
     * <p>
     * This method initializes the application, loads existing tasks, and enters a loop
     * to process user commands until the user exits the application.
     * </p>
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs during task storage operations.
     */
    public static void main(String[] args) throws IOException {
        ui.printStart();
        items = db.getTasks();
        ui.printTasks(items);

        while (true) {
            try {
                String input = ui.nextLine();
                handleInput(input);

                if (Parser.parseCommand(input).equals(Commands.BYE)) {
                    break;
                }
            } catch (CitadelException e) {
                ui.printCitadelException(e);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            } catch (Exception e) {
                ui.printException(e);
            }
        }

        ui.printGoodbye();
        db.saveData(items);
    }
}
