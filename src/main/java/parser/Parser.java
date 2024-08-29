package parser;

import command.*;
import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.RatchetException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Command parse(String input) throws RatchetException {
        String command = input.split(" ")[0].toUpperCase();
        switch (command) {
        case "LIST":
            return new ListCommand();
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
            throw new InvalidCommandException(
                    "Ratchet is unable to " + "execute" + " " + command + "!");
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
                throw new InvalidCommandArgumentException("The description of a deadline task cannot be"
                        + " empty!");
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
                throw new InvalidCommandArgumentException("The description of a deadline task cannot be "
                        + "empty!");
            }
            task = new EventTask(description, from, to);
        }
        return new AddCommand(task);
    }

    private Command mark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(num);
    }

    private Command unmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnmarkCommand(num);
    }

    private Command delete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(num);
    }
}
