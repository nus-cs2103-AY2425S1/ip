package echochat;
import exceptions.EmptyDescriptionError;
import exceptions.InvalidCommandError;

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

        if (isExitCommand(input)) {
            return new Command(CommandType.EXIT, null, 0);
        } else if (isListCommand(input)) {
            return new Command(CommandType.LIST, null, 0);
        } else if (parts.length == 2) {
            return parseActionCommand(parts[0], parts[1]);
        }
        throw new InvalidCommandError();
    }

    /**
     * Checks if the input is an "exit" command.
     *
     * @param input the user input to check
     * @return true if the input is "bye", false otherwise
     */
    private boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    /**
     * Checks if the input is a "list" command.
     *
     * @param input the user input to check
     * @return true if the input is "list", false otherwise
     */
    private boolean isListCommand(String input) {
        return input.equals("list");
    }

    /**
     * Parses action commands like find, mark, unmark, delete, or task creation commands (todo, deadline, event).
     *
     * @param command the command keyword (e.g., mark, unmark, find)
     * @param details the command details (e.g., task number or task description)
     * @return Command object based on the input
     * @throws EmptyDescriptionError if the task description is missing for todo, deadline, or event commands
     * @throws InvalidCommandError if the command is invalid or cannot be parsed
     */
    private Command parseActionCommand(String command, String details) throws EmptyDescriptionError, InvalidCommandError {
        switch (command) {
        case "find":
            return new Command(CommandType.FIND, details, 0);
        case "mark":
            return new Command(CommandType.MARK, null, parseTaskNumber(details));
        case "unmark":
            return new Command(CommandType.UNMARK, null, parseTaskNumber(details));
        case "delete":
            return new Command(CommandType.DELETE, null, parseTaskNumber(details));
        case "todo":
        case "deadline":
        case "event":
            if (details.isEmpty()) {
                throw new EmptyDescriptionError();
            }
            return parseTask(command, details);
        default:
            throw new InvalidCommandError();
        }
    }

    /**
     * Parses the task number from the user input.
     *
     * @param input the input string containing the task number
     * @return the parsed task number
     * @throws InvalidCommandError if the input is not a valid number
     */
    private int parseTaskNumber(String input) throws InvalidCommandError {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidCommandError();
        }
    }

    /**
     * Parses a task command (todo, deadline, event) and returns the corresponding Command object.
     *
     * @param type the type of task (e.g., "todo", "deadline", or "event")
     * @param details the task details, including description and optional dates
     * @return Command object based on the task type and details
     * @throws InvalidCommandError if the task format is incorrect
     * @throws EmptyDescriptionError if the task description is empty
     */
    private Command parseTask(String type, String details) throws InvalidCommandError, EmptyDescriptionError {
        String description = extractDescription(details);
        String by = extractBy(details);
        String from = extractFrom(details);
        String to = extractTo(details);

        if (description.isEmpty()) {
            throw new EmptyDescriptionError();
        }

        Task task = createTask(type, description, by, from, to);
        return new Command(determineCommandType(type), description, 0, task);
    }


    /**
     * Extracts the task description from the details string.
     *
     * @param details the input string containing the task details
     * @return the extracted task description
     */
    private String extractDescription(String details) {
        return details.split("/")[0].trim();
    }

    /**
     * Extracts the "by" date for a deadline task from the details string.
     *
     * @param details the input string containing the task details
     * @return the "by" date if present, null otherwise
     */
    private String extractBy(String details) {
        return extractDetail(details, "by ");
    }

    /**
     * Extracts the "from" time for an event task from the details string.
     *
     * @param details the input string containing the task details
     * @return the "from" time if present, null otherwise
     */
    private String extractFrom(String details) {
        return extractDetail(details, "from ");
    }

    /**
     * Extracts the "to" time for an event task from the details string.
     *
     * @param details the input string containing the task details
     * @return the "to" time if present, null otherwise
     */
    private String extractTo(String details) {
        return extractDetail(details, "to ");
    }

    /**
     * Extracts a detail (such as a date or time) from the task details string.
     *
     * @param details the input string containing the task details
     * @param prefix the prefix to look for (e.g., "by ", "from ", "to ")
     * @return the extracted detail if present, null otherwise
     */
    private String extractDetail(String details, String prefix) {
        for (String part : details.split("/")) {
            part = part.trim();
            if (part.startsWith(prefix)) {
                return part.substring(prefix.length()).trim();
            }
        }
        return null;
    }

    /**
     * Creates a Task object based on the task type and details.
     *
     * @param type the type of task (todo, deadline, or event)
     * @param description the task description
     * @param by the "by" date for deadline tasks (nullable)
     * @param from the "from" time for event tasks (nullable)
     * @param to the "to" time for event tasks (nullable)
     * @return the created Task object
     * @throws InvalidCommandError if the task details are invalid
     */
    private Task createTask(String type, String description, String by, String from, String to) throws InvalidCommandError {
        switch (type) {
        case "todo":
            return new Todo(description);
        case "deadline":
            if (by == null) {
                throw new InvalidCommandError();
            }
            return new Deadline(by, description);
        case "event":
            if (from == null || to == null) {
                throw new InvalidCommandError();
            }
            return new Event(from, to, description);
        default:
            throw new InvalidCommandError();
        }
    }

    /**
     * Determines the CommandType for a task based on its type.
     *
     * @param type the type of task (todo, deadline, or event)
     * @return the corresponding CommandType
     */
    private CommandType determineCommandType(String type) {
        switch (type) {
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        default:
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

}
