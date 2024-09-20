package ratchet.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;

import ratchet.command.AddCommand;
import ratchet.command.Command;
import ratchet.command.DeleteCommand;
import ratchet.command.ExitCommand;
import ratchet.command.FindCommand;
import ratchet.command.ListCommand;
import ratchet.command.MarkCommand;
import ratchet.command.UnmarkCommand;
import ratchet.exception.InvalidCommandArgumentException;
import ratchet.exception.InvalidCommandException;
import ratchet.exception.RatchetException;
import ratchet.task.DeadlineTask;
import ratchet.task.EventTask;
import ratchet.task.Task;
import ratchet.task.TodoTask;

/**
 * Class to handle parsing of user input.
 */
public class Parser {
    private static final int SPLIT_FIRST = 0;
    private static final int SPLIT_SECOND = 1;

    /**
     * Parses user input and returns a Command.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws RatchetException If Ratchet is unable to handle command or arguments are wrong.
     */
    public Command parse(String input) throws RatchetException {
        String command = input.split(" ")[0];
        switch (command.toUpperCase()) {
        case "LIST":
            return new ListCommand();
        case "FIND":
            return find(input);
        case "MARK":
            return mark(input);
        case "UNMARK":
            return unmark(input);
        case "TODO", "DEADLINE", "EVENT":
            return addTask(input);
        case "DELETE":
            return delete(input);
        case "BYE":
            return new ExitCommand();
        default:
            throw new InvalidCommandException("Ratchet is unable to execute " + command + "!");
        }
    }

    private Command find(String text) throws InvalidCommandArgumentException {
        try {
            String keyword = text.substring(5);
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please provide the keyword you want to search for!");
        }
    }

    private Command addTask(String text) throws InvalidCommandArgumentException {
        Task task = null;
        if (text.startsWith("todo")) {
            task = createTodo(text);
        } else if (text.startsWith("deadline")) {
            task = createDeadline(text);
        } else if (text.startsWith("event")) {
            task = createEvent(text);
        } else {
            // Not supposed to reach here
            throw new InvalidCommandArgumentException("Unable to handle this type of task!");
        }
        assert task != null : "task should not be null";
        return new AddCommand(task);
    }

    private Task createTodo(String text) throws InvalidCommandArgumentException {
        String description;
        try {
            description = text.split("todo ")[SPLIT_SECOND];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The description of a todo task cannot be empty!");
        }
        return new TodoTask(description);
    }

    private Task createDeadline(String text) throws InvalidCommandArgumentException {
        String[] firstSplit = text.split(" /by ");
        LocalDate deadline;
        String description;
        try {
            deadline = LocalDate.parse(firstSplit[SPLIT_SECOND]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The deadline of a deadline task cannot be empty!");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgumentException("The format of deadline must be YYYY-MM-DD");
        }
        try {
            description = firstSplit[SPLIT_FIRST].split("deadline ")[SPLIT_SECOND];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The description of a deadline task cannot be empty!");
        }
        return new DeadlineTask(description, deadline);
    }

    private Task createEvent(String text) throws InvalidCommandArgumentException {
        String[] firstSplit = text.split(" /to ");
        LocalDate to;
        LocalDate from;
        String description;
        try {
            to = LocalDate.parse(firstSplit[SPLIT_SECOND]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The to of a event task cannot be empty!");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgumentException("The format of to must be YYYY-MM-DD");
        }
        String[] secondSplit = firstSplit[SPLIT_FIRST].split(" /from ");
        try {
            from = LocalDate.parse(secondSplit[SPLIT_SECOND]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The from of a event task cannot be empty!");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgumentException("The format of from must be YYYY-MM-DD");
        }
        try {
            description = secondSplit[SPLIT_FIRST].split("event ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("The description of a deadline task cannot be empty!");
        }
        return new EventTask(description, from, to);
    }

    private Command mark(String input) throws InvalidCommandArgumentException {
        return new MarkCommand(parseIndex(input));
    }

    private Command unmark(String input) throws InvalidCommandArgumentException {
        return new UnmarkCommand(parseIndex(input));
    }

    private Command delete(String input) throws InvalidCommandArgumentException {
        return new DeleteCommand(parseIndex(input));
    }

    private Integer[] parseIndex(String input) throws InvalidCommandArgumentException {
        try {
            String[] splitByArguments = input.split(" ");
            if (splitByArguments.length < 2) {
                throw new InvalidCommandArgumentException("There is no input given!");
            }
            Integer[] num = new Integer[splitByArguments.length - 1];
            for (int i = SPLIT_SECOND; i < splitByArguments.length; i++) {
                num[i - 1] = Integer.parseInt(splitByArguments[i]) - 1;
            }
            Arrays.sort(num, Collections.reverseOrder());
            return num;
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("Task index must be a number!");
        }
    }
}
