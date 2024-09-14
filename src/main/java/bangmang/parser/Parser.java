package LittleMissHelpful.Parser;

import LittleMissHelpful.Command.*;
import LittleMissHelpful.Exception.InvalidCommandException;

public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        /**
         * Reads user input and returns command
         */
        String[] inputList = fullCommand.split(" ", 2);
        String command = inputList[0].toLowerCase();

        if (inputList.length < 2) {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.equals("help")) {
                return new HelpCommand();
            } else {
                throw new InvalidCommandException("Invalid command. Please provide a valid command.");
            }
        }

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
                    throw new InvalidCommandException("Invalid deadline format. Use 'deadline description /by date'.");
                }
                String deadlineDesc = deadlineParts[0].trim();
                String deadlineBy = deadlineParts[1].trim();
                return new AddDeadlineCommand(deadlineDesc, deadlineBy);

            case "event":
                String[] eventParts = item.split("/from | /to", 3);
                if (eventParts.length < 3) {
                    throw new InvalidCommandException("Invalid event format. Use 'event description /from start /to end'.");
                }
                String eventDesc = eventParts[0].trim();
                String eventFrom = eventParts[1].trim();
                String eventTo = eventParts[2].trim();
                return new AddEventCommand(eventDesc, eventFrom, eventTo);

            default:
                throw new InvalidCommandException("Invalid command. Please provide a valid command.");
        }
    }
}
