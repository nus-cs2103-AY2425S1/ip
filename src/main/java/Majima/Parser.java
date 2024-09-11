package Majima;


import Majima.command.AddDeadlineCommand;
import Majima.command.AddEventCommand;
import Majima.command.AddTodoCommand;
import Majima.command.Command;
import Majima.command.DeleteCommand;
import Majima.command.ExitCommand;
import Majima.command.FindCommand;
import Majima.command.HelpCommand;
import Majima.command.ListCommand;
import Majima.command.MarkCommand;
import Majima.command.UnmarkCommand;
import Majima.task.Task;

public class Parser {

    /**
     * Returns a corresponding command based off the input of the user.
     *
     * @param input The input of the user, to be parsed.
     * @return The corresponding command, or an error if the input is not understood.
     */
    public static Command parse(String input) throws MajimaException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String args = (parts.length > 1) ? parts[1] : "";

        switch (command) {
        case "list":
        return new ListCommand();
        case "todo":
        return parseAddCommand(Task.TaskType.TODO, args);
        case "deadline":
        return parseAddCommand(Task.TaskType.DEADLINE, args);
        case "event":
        return parseAddCommand(Task.TaskType.EVENT, args);
        case "delete":
        return parseDeleteCommand(args);
        case "mark":
        return parseMarkCommand(args);
        case "unmark":
        return parseUnmarkCommand(args);
        case "bye":
        return new ExitCommand();
        case "find":
        return new FindCommand(args);
        case "help":
        return new HelpCommand();
        default:
        throw new MajimaException("Kiryu? I ain't got the faintest idea of what ya just said!");
        }
    }

    private static Command parseAddCommand(Task.TaskType type, String args) throws MajimaException {
        String description;
        switch (type) {
            case TODO:
                if (args.trim().isEmpty()) {
                    throw new MajimaException("Kiryu! Your description can't be empty!");
                }
                return new AddTodoCommand(args);
            case DEADLINE:
                String[] parts = args.split(" /by ", 2); // for Deadline
                if (parts.length < 2) {
                    throw new MajimaException("Kiryu! The deadline command must include a deadline date! Use /by!");
                }
                description = parts[0].trim();
                String deadline = parts[1].trim();
                return new AddDeadlineCommand(description, deadline);
            case EVENT:
                parts = args.split(" /from | /to ", 3);
                if (parts.length < 3) {
                    throw new MajimaException("Kiryu! The event command must include a start and end time, using /from and /to!");
                }
                description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                return new AddEventCommand(description, from, to);
            default:
                throw new MajimaException("Unknown task type found: " + type);
        }
    }

    private static Command parseDeleteCommand(String args) throws MajimaException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new MajimaException("Eh? Kiryu-chan, that ain't no number!");
        }
    }

    private static int parseTaskNumber(String args) {
        return Integer.parseInt(args.trim()) - 1;
    }

    private static Command parseMarkCommand(String args) throws MajimaException {
        int taskNumber = parseTaskNumber(args);
        return new MarkCommand(taskNumber);
    }

    private static Command parseUnmarkCommand(String args) {
        int taskNumber = parseTaskNumber(args);
        return new UnmarkCommand(taskNumber);
    }

}
