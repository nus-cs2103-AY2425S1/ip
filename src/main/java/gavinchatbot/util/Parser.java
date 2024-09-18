package gavinchatbot.util;

import java.time.LocalDate;

import gavinchatbot.command.AddDeadlineCommand;
import gavinchatbot.command.AddEventCommand;
import gavinchatbot.command.AddToDoCommand;
import gavinchatbot.command.Command;
import gavinchatbot.command.CountCommand;
import gavinchatbot.command.DeleteCommand;
import gavinchatbot.command.ExitCommand;
import gavinchatbot.command.FindCommand;
import gavinchatbot.command.ListCommand;
import gavinchatbot.command.MarkCommand;
import gavinchatbot.command.TagCommand;
import gavinchatbot.command.UnmarkCommand;

/**
 * Represents a parser that interprets user input and returns the appropriate command.
 */
public class Parser {
    private final String horizontalLine =
            "_________________________________________________________________________________\n";

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input as a string.
     * @return The command corresponding to the user input.
     * @throws GavinException If the user input is invalid or cannot be parsed.
     */
    public Command parse(String input) throws GavinException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return parseMarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return parseUnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.equalsIgnoreCase("count")) {
            return new CountCommand();
        } else if (input.startsWith("tag")) {
            return parseTagCommand(input);
        } else {
            throw new GavinException(getInvalidInputMessage());
        }
    }

    /**
     * Parses the input for the "mark" command and returns a MarkCommand.
     *
     * @param input The user input as a string.
     * @return A MarkCommand with the specified task index.
     */
    private Command parseMarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(index);
    }

    /**
     * Parses the input for the "unmark" command and returns an UnmarkCommand.
     *
     * @param input The user input as a string.
     * @return An UnmarkCommand with the specified task index.
     */
    private Command parseUnmarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnmarkCommand(index);
    }

    /**
     * Parses the input for the "todo" command and returns an AddToDoCommand.
     *
     * @param input The user input as a string.
     * @return An AddToDoCommand with the specified task description.
     */
    private Command parseTodoCommand(String input) {
        String description = input.substring(5).trim();
        return new AddToDoCommand(description);
    }

    /**
     * Parses the input for the "deadline" command and returns an AddDeadlineCommand.
     *
     * @param input The user input as a string.
     * @return An AddDeadlineCommand with the specified task description and due date.
     */
    private Command parseDeadlineCommand(String input) {
        String[] parts = input.substring(9).split("/by");
        String description = parts[0].trim();
        LocalDate by = LocalDate.parse(parts[1].trim());
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the input for the "event" command and returns an AddEventCommand.
     *
     * @param input The user input as a string.
     * @return An AddEventCommand with the specified task description and time range.
     */
    private Command parseEventCommand(String input) {
        String[] parts = input.substring(6).split("/from");
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to");
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the input for the "delete" command and returns a DeleteCommand.
     *
     * @param input The user input as a string.
     * @return A DeleteCommand with the specified task index.
     */
    private Command parseDeleteCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the input for the "find" command and returns a FindCommand.
     *
     * @param input The user input as a string.
     * @return A FindCommand with the specified keyword.
     */
    private Command parseFindCommand(String input) {
        String find = input.substring(5).trim();
        return new FindCommand(find);
    }

    /**
     * Parses the input for the "tag" command and returns a TagCommand.
     *
     * @param input The user input as a string.
     * @return A TagCommand with the specified task index and tag.
     */
    private Command parseTagCommand(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        String tag = parts[2];
        return new TagCommand(index, tag);
    }

    /**
     * Returns a formatted error message for invalid input.
     *
     * @return A string containing the formatted invalid input message.
     */
    private String getInvalidInputMessage() {
        return horizontalLine
                + "Invalid input!!! \n"
                + "\n"
                + "To add a new task, please start with 'todo', 'deadline', or 'event'. \n"
                + "\n"
                + "To add a 'todo' task, please type 'todo' followed by the task. \n"
                + "\n"
                + "To add a 'deadline' task, please type 'deadline', followed by the task, and '/by YYYY-MM-DD'. \n"
                + "\n"
                + "To add an 'event' task, please type 'event', followed by the task, '/from' a date or time, "
                + "and '/to' a date or time. \n"
                + "\n"
                + "To view the list of tasks, please type 'list'. \n"
                + "\n"
                + "To search for tasks in the TaskList that contain the keyword, "
                + "please type find, followed by the keyword. \n"
                + "\n"
                + "To mark/unmark the tasks, please type 'mark' or 'unmark' , followed by the index of the task. \n"
                + "\n"
                + "To delete an existing task, please type 'delete' , followed by the index of the task. \n"
                + "\n"
                + "To get the count of the number of tasks that are marked as done, please type 'count'. \n"
                + "\n"
                + "To tag a task with a description, please type 'tag', followed by the index of the task, "
                + "followed by the description you want to tag the task with. \n"
                + "\n"
                + "To exit, please type 'bye'. "
                + horizontalLine;
    }
}
