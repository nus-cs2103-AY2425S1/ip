package hypebot.parser.task;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.exception.missing.MissingTaskNameException;
import hypebot.parser.Parser;
import hypebot.parser.datetime.DateTimeParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

/**
 * Represents the base {@code TaskParser} that serves as the basis for all
 * parsers of {@link Task} objects entered by user or decoded from a save file.
 * <p>A child of {@link Parser}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see FileTaskParser
 * @see UiTaskParser
 */
public abstract class TaskParser extends Parser {
    /** {@link DateTimeParser} that handles parsing of date-related entries. */
    protected DateTimeParser dateTimeParser;
    protected enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Takes in a {@link DateTimeParser} and creates a new {@code TaskParser}.
     *
     * @param dateTimeParser {@link DateTimeParser} that handles parsing of
     *                       date-related {@link Task} data.
     */
    public TaskParser(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    /**
     * Takes in {@link String} input and returns the separated input for preparation
     * of extraction for relevant {@link Task} data (e.g. type, name, dates, etc.)
     * according to medium data is entered.
     *
     * @param input Some {@link String} form of input.
     * @return {@link String} array of separated input.
     */
    protected abstract String[] splitLine(String input);

    /**
     * Takes in {@link String} input and returns the corresponding {@code TaskType}
     * specified by input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@code TaskType} specified by input.
     * @throws IllegalTaskTypeException If no accepted {@code TaskType} is parsed.
     */
    protected abstract TaskType extractTaskType(String input) throws IllegalTaskTypeException;

    /**
     * Takes in {@link String} input and returns the {@link Task}'s name
     * specified by the input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@code TaskType} specified by input.
     * @throws MissingTaskNameException If {@link Task} name is missing in input.
     */
    protected abstract String parseTaskName(String input) throws MissingTaskNameException;

    /**
     * Takes in {@link String} input and returns the {@link ToDo} task
     * specified by the input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@link ToDo} specified by input.
     * @throws MissingTaskNameException If {@link ToDo} name is missing in input.
     */
    public abstract ToDo parseToDo(String input) throws MissingTaskNameException;

    /**
     * Takes in {@link String} input and returns the {@link Deadline} task
     * specified by the input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@link Deadline} specified by input.
     * @throws DatePassedException      If entered due date has passed.
     * @throws DueDateParseException    If entered due date is not in an accepted format.
     */
    public abstract Deadline parseDeadline(String input)
            throws DatePassedException, DueDateParseException;

    /**
     * Takes in {@link String} input and returns the {@link Deadline} task
     * specified by the input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@link Deadline} specified by input.
     * @throws DatePassedException         If both entered event times have passed.
     * @throws EventDateTimeParseException If any entered event times are not in an accepted format.
     */
    public abstract Event parseEvent(String input)
            throws DatePassedException, EventDateTimeParseException;

    /**
     * Takes in {@link String} input and returns the {@link Task}
     * specified by the input.
     *
     * @param input Some {@link String} form of input.
     * @return Corresponding {@link Task} specified by input.
     */
    @Override
    public abstract Task parse(String input);
}
