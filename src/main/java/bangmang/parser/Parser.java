package bangmang.parser;

import bangmang.command.*;
import bangmang.exception.InvalidCommandException;

/**
 * The Parser class handles the parsing of user input into specific commands.
 * It reads the user input, validates it, and returns the corresponding Command object
 * based on the command type (e.g., add task, mark task, delete task).
 */

public class Parser {
    /**
     * Parses the full command input from the user and returns the appropriate command object.
     *
     * @param fullCommand The full user input string.
     * @return The command corresponding to the user input.
     * @throws InvalidCommandException If the input is not valid or the command is not recognized.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] inputList = fullCommand.split(" ", 2);
        String command = inputList[0].toLowerCase();

        // If the command is a single word command (like "bye", "list", or "help")
        if (inputList.length < 2) {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("help")) {
                return new HelpCommand();
            } else {
                throw new InvalidCommandException("Alamak, invalid command. Please provide a valid command.");
            }
        }

        // Commands with additional parameters (like "mark", "todo", "deadline")
        String item = inputList[1];
        switch (command) {
            case "mark":
                return new MarkTaskCommand(Integer.parseInt(item));

            case "unmark":
                return new UnmarkTaskCommand(Integer.parseInt(item));

            case "delete":
                return new DeleteTaskCommand(Integer.parseInt(item));

            case "find":
                return new FindCommand(item);

            case "todo":
                return new AddTodoCommand(item);

            case "deadline":
                String[] deadlineParts = item.split("/by", 2);
                if (deadlineParts.length < 2) {
                    throw new InvalidCommandException("Alamak, invalid deadline format. Use 'deadline description /by date'.");
                }
                String deadlineDesc = deadlineParts[0].trim();
                String deadlineBy = deadlineParts[1].trim();
                return new AddDeadlineCommand(deadlineDesc, deadlineBy);

            case "event":
                String[] eventParts = item.split("/from | /to", 3);
                if (eventParts.length < 3) {
                    throw new InvalidCommandException("Alamak, invalid event format. Use 'event description /from start /to end'.");
                }
                String eventDesc = eventParts[0].trim();
                String eventFrom = eventParts[1].trim();
                String eventTo = eventParts[2].trim();
                return new AddEventCommand(eventDesc, eventFrom, eventTo);

            default:
                throw new InvalidCommandException("Alamak, invalid command. Please provide a valid command.");
        }
    }
}
