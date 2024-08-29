public class Parser {
    private Command command;
    private int taskNumber;
    private Task task;

    public Parser(String input) {
        input = input.trim();
        String[] tokens = input.split(" ", 2);
        tokens[0] = tokens[0].trim();
        switch (tokens[0]) {
        case "bye":
            command = Command.BYE;
            break;
        case "list":
            command = Command.LIST;
            break;
        case "mark":
            command = Command.MARK;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as done cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as done must be a positive integer.");
                }
                taskNumber = parsedInt;
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as done must be an integer.");
            }
            break;
        case "unmark":
            command = Command.UNMARK;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as undone cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as undone must be a positive integer.");
                }
                taskNumber = parsedInt;
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as undone must be an integer.");
            }
            break;
        case "delete":
            command = Command.DELETE;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to delete cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to delete must be a positive integer.");
                }
                taskNumber = parsedInt;
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to delete must be an integer.");
            }
            break;
        case "todo":
            command = Command.TODO;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            task = new TodoTask(tokens[1].trim());
            break;
        case "deadline":
            command = Command.DEADLINE;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: deadline
            String[] deadlineTokens = tokens[1].split(" /by ", 2);
            if (deadlineTokens.length < 2) {
                throw new NixyException("BLAHH!!! The deadline of a deadline task must be specified.");
            }
            task = new DeadlineTask(deadlineTokens[0].trim(),
                LocalDate.parse(deadlineTokens[1].trim()));
            break;
        case "event":
            command = Command.EVENT;
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: event times (unparsed)
            String[] eventTokens = tokens[1].split(" /from ", 2);
            if (eventTokens.length < 2) {
                throw new NixyException("BLAHH!!! The start time of an event task must be specified.");
            }
            // idx 0: start time, idx 1: end time
            String[] eventTimeTokens = eventTokens[1].split(" /to ", 2);
            task = new EventTask(
                eventTokens[0].trim(),
                LocalDateTime.parse(eventTimeTokens[0].trim()),
                LocalDateTime.parse(eventTimeTokens[1].trim())
            );
            break;
        default:
            command = Command.INVALID;
            break;
        }
    }

    /**
     * Returns the command from the user input.
     * @return The command from the user input.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns the task number from the user input.
     * Throws if task number does not exist for the command.
     * @return The task number from the user input.
     */
    public int getTaskNumber() {
        if (command != Command.MARK && command != Command.UNMARK && command != Command.DELETE) {
            throw new NixyException("BLAHH!!! The task number does not exist for this command.");
        }
        return taskNumber;
    }

    /**
     * Returns the task from the user input.
     * Throws if task does not exist for the command.
     * @return The task from the user input.
     */
    public Task getTask() {
        if (command != Command.TODO && command != Command.DEADLINE && command != Command.EVENT) {
            throw new NixyException("BLAHH!!! The task does not exist for this command.");
        }
        return task;
    }
}
