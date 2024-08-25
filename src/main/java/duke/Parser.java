package duke;

import duke.command.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for parsing input and storage.
 */
public class Parser {
    /**
     * Parses input and returns the corresponding command.
     *
     * @param input The input message.
     * @return The command corresponding to the input message.
     * @throws BobException If the input is invalid.
     */
    public static Command parseInput(String input) throws BobException {
        if (Objects.equals(input, "")) {
            return Command.noop;
        }

        String response;
        String command = input.split(" ")[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return mark(input);
        case "unmark":
            return unmark(input);
        case "todo":
            return todo(input);
        case "deadline": {
            return deadline(input);
        }
        case "event": {
            return event(input);
        }
        case "delete": {
            return delete(input);
        }
        default:
            throw new BobException("I'm sorry, I did not understand your message.");
        }
    }

    /**
     * Parses a mark message and returns the corresponding mark command.
     *
     * @param input The mark message.
     * @return The mark command corresponding to the mark message.
     * @throws BobException If the input is invalid.
     */
    private static Command mark(String input) throws BobException {
        Matcher matcher = Pattern.compile("^mark (\\d*)$").matcher(input);

        if (!matcher.find()) {
            throw new BobException("Please specify which task to mark.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        return new MarkCommand(itemNum, true);
    }

    /**
     * Parses an unmark message and returns the corresponding mark command.
     *
     * @param input The unmark message.
     * @return The mark command corresponding to the unmark message.
     * @throws BobException If the input is invalid.
     */
    private static Command unmark(String input) throws BobException {
        Matcher matcher = Pattern.compile("^unmark (\\d*)$").matcher(input);

        if (!matcher.find()) {
            throw new BobException("Please specify which task to unmark.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        return new MarkCommand(itemNum, false);
    }

    /**
     * Parses a to-do message and returns the corresponding add command.
     *
     * @param input The to-do message.
     * @return The add command corresponding to the to-do message.
     */
    private static Command todo(String input) {
        String name = input.substring(5);

        Task task = new ToDo(name);

        return new AddCommand(task);
    }

    /**
     * Parses a deadline message and returns the corresponding add command.
     *
     * @param input The deadline message.
     * @return The add command corresponding to the deadline message.
     * @throws BobException If the input is invalid.
     */
    private static Command deadline(String input) throws BobException {
        Matcher matcher = Pattern.compile("^deadline (.*) /by (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new BobException("Please specify the deadline using \"/by\".");
        }

        String name = matcher.group(1);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(matcher.group(2), f);
        } catch (DateTimeParseException e) {
            throw new BobException("Please specify the deadline using the yyyy-MM-ss HH:mm:ss format.");
        }

        Deadline task = new Deadline(name, deadline);
        return new AddCommand(task);
    }

    /**
     * Parses an event message and returns the corresponding add command.
     *
     * @param input The event message.
     * @return The add command corresponding to the event message.
     * @throws BobException If the input is invalid.
     */
    private static Command event(String input) throws BobException {
        Matcher matcher = Pattern.compile("^event (.*) /from (.*) /to (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new BobException("Please specify the timing using \"/from\" and \"/to\".");
        }

        String name = matcher.group(1);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(matcher.group(2), f);
            end = LocalDateTime.parse(matcher.group(3), f);
        } catch (DateTimeParseException e) {
            throw new BobException("Please specify the timing using the yyyy-MM-dd HH:mm:ss format.");
        }

        Event task = new Event(name, start, end);
        return new AddCommand(task);
    }

    /**
     * Parses a delete message and returns the corresponding delete command.
     *
     * @param input The delete message.
     * @return The delete command corresponding to the delete message.
     * @throws BobException If the input is invalid.
     */
    private static Command delete(String input) throws BobException {
        Matcher matcher = Pattern.compile("^delete (\\d*)$").matcher(input);
        if (!matcher.find()) {
            throw new BobException("Please specify which task to delete.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        return new DeleteCommand(itemNum);
    }

    /**
     * Parses a task serialized for storage and returns the corresponding task.
     *
     * @param taskString The task serialized for storage
     * @return The task created from the specified task string.
     * @throws BobException If the task string is invalid.
     */
    public static Task parseStorage(String taskString) throws BobException {
        Matcher todoMatcher = Pattern.compile("^\\[T]\\[([X ])] (.*)$").matcher(taskString);
        Matcher deadlineMatcher = Pattern.compile("^\\[D]\\[([X ])] (.*) \\(by: (.*)\\)$").matcher(taskString);
        Matcher eventMatcher = Pattern.compile("^\\[E]\\[([X ])] (.*) \\(from: (.*) to: (.*)\\)$").matcher(taskString);

        boolean isCompleted;
        Task task;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        try {
            if (todoMatcher.find()) {
                isCompleted = Objects.equals(todoMatcher.group(1), "X");
                String name = todoMatcher.group(2);

                task = new ToDo(name);
            } else if (deadlineMatcher.find()) {
                isCompleted = Objects.equals(deadlineMatcher.group(1), "X");
                String name = deadlineMatcher.group(2);
                LocalDateTime deadline = LocalDateTime.parse(deadlineMatcher.group(3), f);

                task = new Deadline(name, deadline);
            } else if (eventMatcher.find()) {
                isCompleted = Objects.equals(eventMatcher.group(1), "X");
                String name = eventMatcher.group(2);
                LocalDateTime start = LocalDateTime.parse(eventMatcher.group(3), f);
                LocalDateTime end = LocalDateTime.parse(eventMatcher.group(4), f);

                task = new Event(name, start, end);
            } else {
                throw new BobException("Could not parse task.");
            }

            if (isCompleted) {
                task.mark(true);
            }
        } catch (DateTimeParseException e) {
            throw new BobException("Could not parse task.");
        }

        return task;
    }
}
