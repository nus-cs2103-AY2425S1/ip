package chobo;

/**
 * The type Parser.
 */
public class Parser {

    public static Command parse(String fullCommand, TaskList taskList, Ui ui, Storage storage) throws InputException {
        String[] inputs = fullCommand.split(" ", 2);
        String action = inputs[0];
        switch (action) {
        case "list" -> {
            return new ListCommand();
        }
        case "mark", "unmark", "delete" -> {
            return parseModifyCommand(action, inputs, taskList);
        }
        case "todo" -> {
            return parseTodoCommand(inputs);
        }
        case "deadline" -> {
            return parseDeadlineCommand(inputs);
        }
        case "event" -> {
            return parseEventCommand(inputs);
        }
        case "find" -> {
            return parseFindCommand(inputs);
        }
        default -> throw new InputException("Invalid");
        }
    }

    private static Command parseModifyCommand(String action, String[] inputs, TaskList taskList) throws InputException {
        if (inputs.length < 2) {
            throw new InputException("id");
        }
        int taskId;
        try {
            taskId = Integer.parseInt(inputs[1].trim()) - 1;
        } catch (Exception e) {
            throw new InputException("id");
        }
        if (taskId < 0 || taskId >= taskList.getTotalTask()) {
            throw new InputException("id");
        }

        return action.equals("mark")
                ? new MarkCommand(taskId)
                : action.equals("unmark")
                ? new UnmarkCommand(taskId)
                : new DeleteCommand(taskId);
    }

    private static Command parseTodoCommand(String[] inputs) throws InputException {
        if (inputs.length < 2) {
            throw new InputException("todo");
        }
        return new AddToDoCommand(inputs[1].trim());
    }

    private static Command parseDeadlineCommand(String[] inputs) throws InputException {
        if (inputs.length < 2) {
            throw new InputException("deadline");
        }
        String[] deadlineParts = inputs[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new InputException("deadline");
        }
        return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    private static Command parseEventCommand(String[] inputs) throws InputException {
        if (inputs.length < 2) {
            throw new InputException("event");
        }
        String[] eventParts = inputs[1].split("/from", 2);
        if (eventParts.length < 2) {
            throw new InputException("event");
        }
        String[] dates = eventParts[1].split("/to", 2);
        if (dates.length < 2) {
            throw new InputException("event");
        }
        return new AddEventCommand(eventParts[0].trim(), dates[0].trim(), dates[1].trim());
    }

    private static Command parseFindCommand(String[] inputs) throws InputException {
        if (inputs.length < 2) {
            throw new InputException("find");
        }
        return new FindCommand(inputs[1].trim());
    }
}

