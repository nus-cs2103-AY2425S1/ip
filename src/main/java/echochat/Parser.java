package echochat;
import Exceptions.EmptyDescriptionError;
import Exceptions.InvalidCommandError;

public class Parser {

    /**
     * Returns a mark/unmark/delete Command based on the first word of user input, or calls parseTask()
     * @param input the entire user input to be parsed
     * @return Command with task type mark/unmark/delete
     * @throws EmptyDescriptionError
     * @throws InvalidCommandError
     */
    public Command parse(String input) throws EmptyDescriptionError, InvalidCommandError {
        String[] parts = input.split(" ", 2);

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
                if (parts[1].equals("")) {
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
     * Returns a Command with task type todo/deadline/event type with correct details.
     * @param type either "todo", "deadline" or "event"
     * @param details String that possible contains dates to be parsed
     * @return Command with task type todo/deadline/event
     * @throws InvalidCommandError
     * @throws EmptyDescriptionError
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
                    from = detail.substring(5);
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
            assert by != null : "Deadline task requires a 'by' date";
            task = new Deadline(by, description);
            break;
        case "event":
            assert from != null && to != null : "Event task requires 'from' and 'to' dates";
            task = new Event(from, to, description);
            break;
        default:
            break;
        }

        assert task != null : "Task should have been initialized for type: " + type;
        return new Command(type.equals("todo") ? CommandType.TODO : type.equals("deadline") ? CommandType.DEADLINE : CommandType.EVENT, description, 0, task);
    }
}
