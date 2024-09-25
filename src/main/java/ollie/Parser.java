package ollie;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ollie.command.AddCommand;
import ollie.command.Command;
import ollie.command.DeleteCommand;
import ollie.command.ExitCommand;
import ollie.command.FindCommand;
import ollie.command.ListCommand;
import ollie.command.MarkCommand;
import ollie.command.UndoCommand;
import ollie.command.UnmarkCommand;
import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Task;
import ollie.task.Todo;

/**
 * Represents a parser which interprets the user's inputs using regular
 * expressions, and translate them into commands.
 */
public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parse user's input matches it to a command (if any).
     * E.g. Parser.parse("todo homework") will return a AddCommand for adding of the Todo task.
     *
     * @param input User's input.
     * @return Command to be executed.
     * @throws OllieException If input cannot be appropriately mapped to any commands.
     */
    public static Command parse(String input) throws OllieException {

        if (input.matches("list")) {
            return new ListCommand();
        } else if (input.matches("^mark.*")) {
            int index = Parser.getIndex(input);
            return new MarkCommand(index);
        } else if (input.matches("^unmark.*")) {
            int index = Parser.getIndex(input);
            return new UnmarkCommand(index);
        } else if (input.matches("^(deadline|event|todo).*")) {
            Task task = Parser.parseTask(input);
            return new AddCommand(task);
        } else if (input.matches("^delete.*")) {
            int index = Parser.getIndex(input);
            return new DeleteCommand(index);
        } else if (input.matches("^find.*")) {
            String findQuery = Parser.getBody("find", input);
            return new FindCommand(findQuery);
        } else if (input.matches("undo")) {
            return new UndoCommand();
        } else if (input.matches("bye")) {
            return new ExitCommand();
        } else {
            throw new OllieException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int getIndex(String s) throws OllieException {
        String[] splitString = s.split(" ", 2);
        if (splitString[1].isEmpty()) {
            throw new OllieException("Missing serial number after command!");
        }

        try {
            return Integer.parseInt(splitString[1]) - 1;
        } catch (NumberFormatException e) {
            throw new OllieException("Invalid serial number given!");
        }
    }

    private static Task parseTask(String s) throws OllieException {
        if (s.matches("^deadline.*$")) {
            return parseDeadline(s);
        } else if (s.matches("^event.*")) {
            return parseEvent(s);
        } else if (s.matches("^todo.*")) {
            return parseTodo(s);
        } else {
            throw new OllieException("Invalid Task");
        }
    }

    private static Deadline parseDeadline(String s) throws OllieException {
        if (!s.contains("/by")) {
            throw new OllieException("Use deadline with a \"/by\" keyword and a date.");
        }

        String[] splitString = s.split("/by", 2);
        String byInString = splitString[1].trim();
        String desc = splitString[0].replaceFirst("deadline", "").trim();
        if (desc.isEmpty()) {
            throw new OllieException("Description of deadline cannot be empty!");
        }
        if (byInString.isEmpty()) {
            throw new OllieException("Date of deadline cannot be empty!");
        }

        LocalDate by;
        try {
            by = LocalDate.parse(byInString, Parser.formatter);
        } catch (DateTimeException e) {
            throw new OllieException("Date must be valid and strictly formatted as yyyy-mm-dd !");
        }
        assert (by != null);
        return new Deadline(desc, by);
    }

    private static Event parseEvent(String s) throws OllieException {
        if (!s.contains("/from")) {
            throw new OllieException("Use deadline with a \"/from\" keyword and a date.");
        }
        if (!s.contains("/to")) {
            throw new OllieException("Use deadline with a \"/to\" keyword and a date.");
        }
        if (!s.matches(".*/from.*/to.*")) {
            throw new OllieException("\"/from\" keyword must come before \"/to\" keyword.");
        }

        String[] splitString = s.split("/from|/to", 3);
        String desc = splitString[0].replaceFirst("event", "").trim();
        String fromInString = splitString[1].trim();
        String toInString = splitString[2].trim();
        if (desc.isEmpty()) {
            throw new OllieException("Description of event cannot be empty!");
        }
        if (fromInString.isEmpty()) {
            throw new OllieException("Date after /from cannot be empty!");
        }
        if (toInString.isEmpty()) {
            throw new OllieException("Date after /to cannot be empty!");
        }

        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(fromInString, Parser.formatter);
            to = LocalDate.parse(toInString, Parser.formatter);
        } catch (DateTimeException e) {
            throw new OllieException("Date must be valid and strictly formatted as yyyy-mm-dd !");
        }
        assert (from != null);
        assert (to != null);
        if (!from.isBefore(to)) {
            throw new OllieException("/from's date must be before /to's date!");
        }
        return new Event(desc, from, to);
    }

    private static Todo parseTodo(String s) throws OllieException {
        String desc = s.replaceFirst("todo", "").trim();
        if (desc.isEmpty()) {
            throw new OllieException("Description of todo cannot be empty!");
        }
        return new Todo(desc);
    }

    private static String getBody(String prefix, String s) throws OllieException {
        String body = s.replaceFirst(prefix, "").trim();
        if (body.isEmpty()) {
            throw new OllieException("Cannot have an empty body after command!");
        }
        return body;
    }
}
