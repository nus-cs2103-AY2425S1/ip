package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RestoreCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;



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
            return Command.NOOP;
        }

        String command = input.split(" ")[0];
        return switch (command) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> mark(input);
        case "unmark" -> unmark(input);
        case "todo" -> todo(input);
        case "deadline" -> deadline(input);
        case "event" -> event(input);
        case "delete" -> delete(input);
        case "find" -> find(input);
        case "archive" -> new ArchiveCommand();
        case "restore" -> new RestoreCommand();
        default -> throw new BobException("I'm sorry, I did not understand your message.");
        };
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
    private static Command todo(String input) throws BobException {
        Matcher matcher = Pattern.compile("^todo (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new BobException("Please specify the to do task name.");
        }
        String name = matcher.group(1);

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
     * Parses a find message and returns the corresponding find command.
     *
     * @param input The find message.
     * @return The find command corresponding to the find message.
     */
    private static Command find(String input) throws BobException {
        Matcher matcher = Pattern.compile("^find (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new BobException("Please specify the keyword to search for.");
        }
        String keyword = matcher.group(1);
        return new FindCommand(keyword);
    }

    /**
     * Parses a task serialized for storage and returns the corresponding task.
     *
     * @param taskString The task serialized for storage
     * @return The task created from the specified task string.
     * @throws BobException If the task string is invalid.
     */
    public static Task parseStorage(String taskString) throws BobException {
        ToDo todo = parseTodo(taskString);
        if (todo != null) {
            return todo;
        }

        Deadline deadline = parseDeadline(taskString);
        if (deadline != null) {
            return deadline;
        }

        Event event = parseEvent(taskString);
        if (event != null) {
            return event;
        }

        throw new BobException("Could not parse task.");
    }

    private static ToDo parseTodo(String taskString) {
        Matcher todoMatcher = Pattern.compile("^\\[T]\\[([X ])] (.*)$").matcher(taskString);

        if (!todoMatcher.find()) {
            return null;
        }

        boolean isCompleted = Objects.equals(todoMatcher.group(1), "X");
        String name = todoMatcher.group(2);

        ToDo task = new ToDo(name);
        if (isCompleted) {
            task.mark(true);
        }

        return task;
    }

    private static Deadline parseDeadline(String taskString) throws BobException {
        Matcher deadlineMatcher = Pattern.compile("^\\[D]\\[([X ])] (.*) \\(by: (.*)\\)$").matcher(taskString);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        if (!deadlineMatcher.find()) {
            return null;
        }

        boolean isCompleted = Objects.equals(deadlineMatcher.group(1), "X");
        String name = deadlineMatcher.group(2);
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(deadlineMatcher.group(3), f);
        } catch (DateTimeParseException e) {
            throw new BobException("Could not parse task.");
        }

        Deadline task = new Deadline(name, deadline);
        if (isCompleted) {
            task.mark(true);
        }

        return task;
    }

    private static Event parseEvent(String taskString) throws BobException {
        Matcher eventMatcher = Pattern.compile("^\\[E]\\[([X ])] (.*) \\(from: (.*) to: (.*)\\)$")
                .matcher(taskString);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        if (!eventMatcher.find()) {
            return null;
        }

        boolean isCompleted = Objects.equals(eventMatcher.group(1), "X");
        String name = eventMatcher.group(2);
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(eventMatcher.group(3), f);
            end = LocalDateTime.parse(eventMatcher.group(4), f);
        } catch (DateTimeParseException e) {
            throw new BobException("Could not parse task.");
        }

        Event task = new Event(name, start, end);
        if (isCompleted) {
            task.mark(true);
        }

        return task;
    }
}
