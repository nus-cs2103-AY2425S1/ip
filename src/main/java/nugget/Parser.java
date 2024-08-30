package nugget;

public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    public Command parse(String command) throws NuggetException {
        String[] splitText = command.split(" ", 2);

        if (splitText.length < 2) {
            if (splitText[0].equals("list")) {
                return new ListCommand();
            } else if (splitText[0].equals("todo")) {
                throw new EmptyDescriptionException();
            } else if (splitText[0].equals("deadline")) {
                throw new EmptyDescriptionException();
            } else if (splitText[0].equals("event")) {
                throw new EmptyDescriptionException();
            }
            throw new UnknownCommandException();
        }

        String action = splitText[0].trim();
        String details = splitText.length > 1 ? splitText[1].trim() : "";
        switch (action) {
        case "mark":
            return new MarkTaskCommand(parseIndex(details));
        case "unmark":
            return new UnmarkTaskCommand(parseIndex(details));
        case "delete":
            return new DeleteTaskCommand(parseIndex(details));
        case "todo":
            return new AddTaskCommand(new Todo(details));
        case "deadline":
            return new AddTaskCommand(parseDeadline(details));
        case "event":
            return new AddTaskCommand(parseEvent(details));
        default:
            throw new UnknownCommandException();
        }
    }

    private int parseIndex(String details) throws NuggetException {
        try {
            int index = Integer.parseInt(details.trim()) - 1;
            if ((index < 0) || (index >= tasks.size())) {
                throw new InvalidTaskNumberException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException();
        }
    }

    private Task parseDeadline(String details) throws NuggetException {
        String[] parts = details.split(" /by ", 2);

        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    private Task parseEvent(String details) throws NuggetException {
        String[] parts = details.split(" /from ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new EmptyDescriptionException();
        }
        return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }
}
