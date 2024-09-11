package Gary;

import Gary.command.AddCommand;
import Gary.command.ByeCommand;
import Gary.command.Command;
import Gary.command.DeleteCommand;
import Gary.command.EditTaskCommand;
import Gary.command.FindCommand;
import Gary.command.ShowListCommand;
import Gary.task.Deadline;
import Gary.task.Event;
import Gary.task.ToDo;


/**
 * The {@code Parser} class is responsible for interpreting user input commands and
 * converting them into specific {@code Command} objects that can be executed.
 */
public class Parser {

    /**
     * Parses a full user command and returns the corresponding {@code Command} object.
     *
     * @param fullCommand The full string input from the user.
     * @return A {@code Command} object representing the user input.
     * @throws GaryException If the command is not recognized or has invalid format.
     */
    static Command parse(String fullCommand) throws GaryException {
        String[] split = fullCommand.trim().split(" ", 2);
        String taskType = split[0];

        switch (taskType.toLowerCase()) {
        case "todo":
            if (split.length != 2) {
                throw new GaryException("Please provide your ToDo task in the following format:\n"
                        + "todo <task name>\n");
            }
            return new AddCommand(new ToDo(split[1].trim()));
        case "deadline":
            if (split.length != 2) {
                throw new GaryException("Please provide your Deadline task in the following format:\n"
                        + "deadline <task name> /by <yyyy-mm-dd>\n");
            }
            String[] split1 = split[1].split("/by");
            if (split1.length != 2) {
                throw new GaryException("Please provide your Deadline task in the following format:\n"
                        + "deadline <task name> /by <yyyy-mm-dd>\n");
            }
            String description = split1[0].trim();
            String dueDate = split1[1].trim();
            if (dueDate.isEmpty() || description.isEmpty()) {
                throw new GaryException("Please provide your Deadline task in the following format:\n"
                        + "deadline <task name> /by <yyyy-mm-dd>\n");
            }
            return new AddCommand(new Deadline(description, dueDate));
        case "event":
            if (split.length != 2) {
                throw new GaryException("Please provide your Event task in the following format:\n"
                        + "event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n");
            }
            String[] firstSplit = split[1].trim().split("/from");
            if (firstSplit.length != 2) {
                throw new GaryException("Please provide your Event task in the following format:\n"
                        + "event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n");
            }
            String[] secondSplit = firstSplit[1].trim().split("/to");
            if (secondSplit.length != 2) {
                throw new GaryException("Please provide your Event task in the following format:\n"
                        + "event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n");
            }
            String eventName = firstSplit[0].trim();
            String start = secondSplit[0].trim();
            String end = secondSplit[1].trim();
            if (eventName.isEmpty() || start.isEmpty() || end.isEmpty()) {
                throw new GaryException("Please provide your Event task in the following format:\n"
                        + "event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n");
            }
            return new AddCommand(new Event(eventName, start, end));
        case "mark":
            return new EditTaskCommand(true, Integer.parseInt(split[1].trim()) - 1);
        case "unmark":
            return new EditTaskCommand(false, Integer.parseInt(split[1].trim()) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(split[1].trim()) - 1);
        case "list":
            return new ShowListCommand();
        case "find":
            if (split.length != 2) {
                throw new GaryException("Please provide a keyword to search for tasks:\n"
                        + "find <keyword>\n");
            }
            return new FindCommand(split[1].trim());
        case "bye":
            return new ByeCommand();
        default:
            throw new GaryException("Sorry! I do not understand what is this!!");
        }
    }
}

