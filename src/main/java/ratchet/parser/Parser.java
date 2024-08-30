package ratchet.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            try {
                String description = text.split("todo ")[1];
                task = new TodoTask(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The description of a todo task cannot be empty!");
            }
        } else if (text.startsWith("deadline")) {
            String[] split = text.split(" /by ");
            LocalDate deadline;
            String description;
            try {
                deadline = LocalDate.parse(split[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The deadline of a deadline task cannot be empty!");
            } catch (DateTimeParseException e) {
                throw new InvalidCommandArgumentException("The format of deadline must be YYYY-MM-DD");
            }
            try {
                description = split[0].split("deadline ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The description of a deadline task cannot be "
                        + "empty!");
            }
            task = new DeadlineTask(description, deadline);
        } else {
            String[] split1 = text.split(" /to ");
            LocalDate to;
            LocalDate from;
            String description;
            try {
                to = LocalDate.parse(split1[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The to of a event task cannot be empty!");
            } catch (DateTimeParseException e) {
                throw new InvalidCommandArgumentException("The format of to must be YYYY-MM-DD");
            }
            String[] split2 = split1[0].split(" /from ");
            try {
                from = LocalDate.parse(split2[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The from of a event task cannot be empty!");
            } catch (DateTimeParseException e) {
                throw new InvalidCommandArgumentException("The format of from must be YYYY-MM-DD");
            }
            try {
                description = split2[0].split("event ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException(
                        "The description of a deadline task cannot be " + "empty!");
            }
            task = new EventTask(description, from, to);
        }
        return new AddCommand(task);
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

    private int parseIndex(String input) throws InvalidCommandArgumentException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException("Task index must be a number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("An index must be given to this command!");
        }
    }
}
