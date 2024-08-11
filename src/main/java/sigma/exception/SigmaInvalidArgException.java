package sigma.exception;

import sigma.command.CommandType;
public class SigmaInvalidArgException extends SigmaException {
    private CommandType command;

    public SigmaInvalidArgException(CommandType command) {
        this.command = command;
    }

    @Override
    public String toString() {
        String message = "%s Invalid argument(s). Usage: %s";
        switch (command) {
            case TODO:
                return String.format(message, super.toString(), "todo <description>");
            case DEADLINE:
                return String.format(message, super.toString(), "deadline <description> /by <date>");
            case EVENT:
                return String.format(message, super.toString(), "event <description> /from <start> /to <end>");
            case MARK:
                return String.format(message, super.toString(), "mark <task_number>");
            case UNMARK:
                return String.format(message, super.toString(), "unmark <task_number>");
            case DELETE:
                return String.format(message, super.toString(), "delete <task_number>");
            default:
                return super.toString() + " Invalid argument(s).";
        }
    }
}
