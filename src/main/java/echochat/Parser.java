package echochat;

import Exceptions.EmptyDescriptionError;
import Exceptions.InvalidCommandError;

/**
 * Parses user input to determine the appropriate Command.
 */
public class Parser {

    /**
     * Parses the user input and returns a corresponding Command.
     *
     * @param input the entire user input to be parsed
     * @return Command with task type mark/unmark/delete or other commands
     * @throws EmptyDescriptionError if the input does not contain a valid description where required
     * @throws InvalidCommandError if the input is not recognized as a valid command
     */
    public Command parse(String input) throws EmptyDescriptionError, InvalidCommandError {
        String[] parts = input.split(" ", 2);

        // Assert that the input split resulted in at least one part
        assert parts.length > 0 : "Input split resulted in an empty array";

        if (input.equals("bye")) {
            return new Command(CommandType.EXIT, null, 0);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST, null, 0);
        } else if (parts.length == 2) {
            switch (parts[0]) {
            case "find":
                return new Command(CommandType.FIND, parts[1], 0);
            case "mark":
                return new Command(CommandType.MARK, null, Integer.parseInt(parts[1]));
            case "unmark":
                return new Command(CommandType.UNMARK, null, Integer.parseInt(parts[1]));
            case "delete":
                return new Command(CommandType.DELETE, null, Integer.parseInt(parts[1]));
            case "todo":
            case "deadline":
            case "event":
                if (parts[1].trim().isEmpty()) {
                    throw new EmptyDescriptionError();
                }
                return parseTask(parts[0], parts[1]);
            default:
                throw new InvalidCommandError();
            }
        }
        throw new InvalidCommandError();
    }

    /**
     * Parses the details of a task and returns a Command with the appropriate task type.
     *
     * @param type either "todo", "deadline" or "event"
     * @param details String that contains the description and optional dates
     * @return Command with the specified task type
     * @throws InvalidCommandError if the type is invalid
     * @throws EmptyDescriptionError if the description is empty or missing where required
     */
    private Command parseTask(String type, String details) throws InvalidCommandError, EmptyDescriptionError {
        assert type.equals("todo") || type.equals("deadline") || type.equals("event") : "Unknown task type: " + type;

        String description = "";
        String by = null;
        String from = null;
        String to = null;

        if (details.contains("/")) {
            String[] splitDetails = details.split("/");
            description = splitDetails[0].trim();

            for (int i = 1; i < splitDetails.length; i++) {
                String detail = splitDetails[i].trim();
                if (detail.startsWith("by ")) {
                    by = detail.substring(3).trim();
                } else if (detail.startsWith("from ")) {
                    from = detail.substring(5).trim();
                } else if (detail.startsWith("to ")) {
                    to = detail.substring(3).trim();
                }
            }
        } else {
            description = details;
        }

        if (description.isEmpty()) {
            throw new EmptyDescriptionError();
        }

        Task task = null;
        switch (type) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            if (by == null) {
                throw new EmptyDescriptionError(); // Custom message in the exception
            }
            task = new Deadline(by, description);
            break;
        case "event":
            if (from == null || to == null) {
                throw new EmptyDescriptionError(); // Custom message in the exception
            }
            task = new Event(from, to, description);
            break;
        default:
            throw new InvalidCommandError(); // Custom message in the exception
        }

        assert task != null : "Task should have been initialized for type: " + type;
        return new Command(
                type.equals("todo") ? CommandType.TODO :
                        type.equals("deadline") ? CommandType.DEADLINE : CommandType.EVENT,
                description, 0, task
        );
    }

}
