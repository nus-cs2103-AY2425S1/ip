package citadel;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import citadel.commands.*;
import citadel.Task.TaskList;
import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidCommandException;
import citadel.ui.TextUI;

/**
 * The {@code Citadel} class represents the main entry point of the Citadel application.
 * It manages user interactions, processes commands, and coordinates tasks and data storage.
 */
public class Citadel {

    /** The list of tasks managed by the application. */
    public static TaskList items = new TaskList();

    /** The storage system used to persist tasks between sessions. */
    public static Storage db = new Storage("data/citadel");

    /** The user interface used to interact with the user. */
    public static TextUI ui = new TextUI();

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
                Commands command = Parser.parseCommand(input);

                switch (command) {
                case BYE:
                    break;

                case LIST:
                    ui.printTasks(items);
                    continue;

                case MARK:
                    new MarkTask(input, items).run();
                    break;

                case UNMARK:
                    new UnmarkTask(input, items).run();
                    break;

                case DELETE:
                    new DeleteTask(input, items).run();
                    break;

                case DEADLINE:
                    new HandleDeadline(input, items).run();
                    break;

                case EVENT:
                    new HandleEvent(input, items).run();
                    break;

                case TODO:
                    new HandleTodo(input, items).run();
                    break;

                case FIND:
                    new FindTask(input, items).run();
                    break;

                default:
                    throw new CitadelInvalidCommandException();
                }

                if (command.equals(Commands.BYE)) {
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







