package bot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bot.action.Action;
import bot.action.AddTaskAction;
import bot.action.DeleteTaskAction;
import bot.action.ExitAction;
import bot.action.ListTaskAction;
import bot.action.MarkTaskAction;
import bot.action.SearchTaskAction;
import bot.action.UndoAction;
import bot.action.UnmarkTaskAction;
import bot.enums.Command;
import bot.exceptions.BotException;
import bot.exceptions.InvalidCommandException;
import bot.exceptions.InvalidDatetimeException;
import bot.exceptions.InvalidTaskDescriptionException;
import bot.exceptions.InvalidTaskIdException;
import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Todo;

/**
 * Represents a parser that is used to parse all string inputs
 * into <code>Action</code> that the bot can execute.
 */
public class Parser {
    /**
     * Parses the given string input from user
     *
     * @param input String input from user
     * @return an executable <code>Command</code>
     * @throws InvalidCommandException Invalid command
     */
    public Action parseInput(String input) throws BotException {
        Pattern regex = Pattern.compile("(\\w+)\\s*(.*)");
        Matcher matcher = regex.matcher(input);
        if (matcher.matches()) {
            Command cmd = Command.fromString(matcher.group(1));
            String args = matcher.group(2).strip();

            // We disable checkstyle for indentation here because checkstyle
            // does not recognise the arrow-notation by default.
            // See issue: https://github.com/nus-cs2103-AY2425S1/forum/issues/184

            // CHECKSTYLE.OFF: Indentation
            return switch (cmd) {
                case LIST -> new ListTaskAction();
                case TODO -> new AddTaskAction(new Todo(args));
                case DEADLINE -> new AddTaskAction(parseDeadlineTask(args));
                case EVENT -> new AddTaskAction(parseEventTask(args));
                case DELETE -> new DeleteTaskAction(parseTaskId(args));
                case MARK -> new MarkTaskAction(parseTaskId(args));
                case UNMARK -> new UnmarkTaskAction(parseTaskId(args));
                case FIND -> new SearchTaskAction(args);
                case UNDO -> new UndoAction();
                case EXIT -> new ExitAction();
            };
            // CHECKSTYLE.ON: Indentation
        } else {
            throw new InvalidCommandException(input);
        }
    }

    /**
     * Parses the given string input from user for creating a new deadline task.
     *
     * @param args String input from user
     * @return <code>Deadline</code> task
     * @throws InvalidTaskDescriptionException Invalid task description cannot be parsed
     */
    public Deadline parseDeadlineTask(String args) throws InvalidTaskDescriptionException, InvalidDatetimeException {
        Pattern regex = Pattern.compile("(.*)\\s/by\\s(.*)");
        Matcher matcher = regex.matcher(args);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String deadline = matcher.group(2);
            return new Deadline(task, parseDate(deadline));
        } else {
            throw new InvalidTaskDescriptionException(args);
        }
    }

    /**
     * Parses the given string input from user for creating a new event task.
     *
     * @param args String input from user
     * @return <code>Event</code> task
     * @throws InvalidTaskDescriptionException Invalid task description cannot be parsed
     */
    public Event parseEventTask(String args) throws InvalidTaskDescriptionException, InvalidDatetimeException {
        Pattern regex = Pattern.compile("(.*)\\s/from\\s(.*)\\s/to\\s(.*)");
        Matcher matcher = regex.matcher(args);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new Event(task, parseDate(from), parseDate(to));
        } else {
            throw new InvalidTaskDescriptionException(args);
        }
    }

    private int parseTaskId(String input) throws BotException {
        String[] arr = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(arr[0]);
            if (taskIndex <= 0) {
                throw new InvalidTaskIdException(arr[0]);
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(arr[0]);
        }
    }

    private LocalDate parseDate(String input) throws InvalidDatetimeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidDatetimeException(input);
        }
    }
}
