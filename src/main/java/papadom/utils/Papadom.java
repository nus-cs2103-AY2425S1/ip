package papadom.utils;

import java.util.Scanner;

import papadom.commands.AddDeadlineCommand;
import papadom.commands.AddEventCommand;
import papadom.commands.AddTodoCommand;
import papadom.commands.Command;
import papadom.commands.DeleteEventCommand;
import papadom.commands.ExitCommand;
import papadom.commands.FindEventCommand;
import papadom.commands.ListCommand;
import papadom.commands.MarkCommand;
import papadom.commands.UnmarkCommand;
import papadom.exceptions.UnknownCommandException;
import papadom.storage.Storage;
import papadom.storage.TaskList;


/**
 * Main class for the Papadom chatbot.
 * This class handles user input and executes the corresponding commands.
 */
public class Papadom {
    /**
     * Enum representing the different types of commands the chatbot can recognize.
     */
    enum CommandType {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;
        /**
         * Converts a string command to a corresponding CommandType enum.
         *
         * @param command The string representation of the command.
         * @return The CommandType enum corresponding to the command.
         */
        public static CommandType fromString(String command) {
            assert command != null && !command.isEmpty() : "Command cannot be null or empty";
            return switch (command.toLowerCase()) {
            case "list" -> LIST;
            case "bye" -> BYE;
            case "mark" -> MARK;
            case "unmark" -> UNMARK;
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            case "delete" -> DELETE;
            case "find" -> FIND;
            default -> UNKNOWN;
            };
        }
    }
    private static final String STORAGE_PATH = "tasks.txt";
    private static final Storage STORAGE = new Storage(STORAGE_PATH);
    private static final TaskList TASK_LIST = new TaskList(STORAGE);
    private static final Ui UI = new Ui();
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Runs the Papadom chatbot, handling user input and executing commands in a loop. Not required actually
     */
    private static void run() {
        System.out.println(Ui.welcomeMessage());
        STORAGE.createFileIfNotPresent();

        // Ensure there's input before calling nextLine()
        while (SCANNER.hasNextLine()) {
            String input = SCANNER.nextLine();
            String response = Papadom.getResponse(input);
            // Redundant
            System.out.println(response);
        }
    }

    public static String getResponse(String input) {
        Command command;
        try {
            String commandText = input.split(" ")[0];
            CommandType commandType = CommandType.fromString(commandText);
            command = switch (commandType) {
            case LIST -> new ListCommand();
            case BYE -> new ExitCommand();
            case MARK -> new MarkCommand(input);
            case UNMARK -> new UnmarkCommand(input);
            case TODO -> new AddTodoCommand(input);
            case DEADLINE -> new AddDeadlineCommand(input);
            case EVENT -> new AddEventCommand(input);
            case DELETE -> new DeleteEventCommand(input);
            case FIND -> new FindEventCommand(input);
            default -> throw new UnknownCommandException();
            };
            // Execute the command and capture the output
            return command.execute(TASK_LIST, UI, STORAGE);
        } catch (Exception e) {
            return UI.output(e.getMessage()); // Return the exception message if there's an error
        }
    }


    /**
     * Main entry point for the Papadom application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Papadom.run();
    }
}
