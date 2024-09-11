package ollie;

import ollie.command.AddCommand;
import ollie.command.Command;
import ollie.command.DeleteCommand;
import ollie.command.ExitCommand;
import ollie.command.FindCommand;
import ollie.command.ListCommand;
import ollie.command.MarkCommand;
import ollie.command.UnmarkCommand;
import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.Task;
import ollie.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        } else if (input.matches("bye")) {
            return new ExitCommand();
        } else {
            throw new OllieException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int getIndex(String s) throws OllieException {
        if (!s.matches(".* \\d+")) {
            throw new OllieException("Missing Serial Number after command.");
        }
        return Integer.parseInt(s.replaceAll("\\D+", "")) - 1;
    }

    private static Task parseTask(String s) throws OllieException {
        Task task;

        // Input parser:
        if (s.matches("^deadline.*$")) {
            if (!s.contains("/by")) {
                throw new OllieException("Use deadline with a \"/by\" keyword and a date.");
            }
            String[] splitString = s.split("/by", 2);

            String desc = splitString[0].replaceFirst("deadline", "").trim();
            if (desc.isEmpty()) {
                throw new OllieException("Description of deadline cannot be empty!");
            }

            String byInString = splitString[1].trim();
            if (byInString.isEmpty()) {
                throw new OllieException("date of deadline cannot be empty!");
            }

            LocalDate by;
            try {
                by = LocalDate.parse(byInString, Parser.formatter);
            } catch (DateTimeException e) {
                throw new OllieException("Date must be valid and strictly formatted as yyyy-mm-dd !");
            }
            assert(by != null);
            task = new Deadline(desc, by);
        } else if (s.matches("^event.*")) {
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
            if (desc.isEmpty()) {
                throw new OllieException("Description of event cannot be empty!");
            }

            String fromInString = splitString[1].trim();
            if (fromInString.isEmpty()) {
                throw new OllieException("date after /from cannot be empty!");
            }
            String toInString = splitString[2].trim();
            if (toInString.isEmpty()) {
                throw new OllieException("date after /to cannot be empty!");
            }

            LocalDate from, to;
            try {
                from = LocalDate.parse(fromInString, Parser.formatter);
                to = LocalDate.parse(toInString, Parser.formatter);
            } catch (DateTimeException e) {
                throw new OllieException("Date must be valid and strictly formatted as yyyy-mm-dd !");
            }
            assert(from != null);
            assert(to != null);
            task = new Event(desc, from, to);
        } else {
            String desc = s.replaceFirst("todo", "").trim();
            if (desc.isEmpty()) {
                throw new OllieException("Description of todo cannot be empty!");
            }

            task = new Todo(desc);
        }
        assert(task != null);
        return task;
    }

    private static String getBody(String prefix, String s) throws OllieException {
        String body = s.replaceFirst(prefix, "").trim();
        if (body.isEmpty()) {
            throw new OllieException("Cannot have an empty body after command!");
        }
        return body;
    }
}
