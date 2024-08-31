package parsers;

import tasks.Task;

import java.time.format.DateTimeFormatter;

/**
 * An abstract class that represents a parser for interpreting task information.
 *
 * <p>The {@code Parser} class provides a base for parsing task-related data
 * and converting it into {@link Task} objects. It includes a predefined date format
 * and requires subclasses to implement the {@link #parse(String[])} method
 * to handle specific parsing logic.</p>
 */
public abstract class Parser {

    /**
     * A {@link DateTimeFormatter} for formatting and parsing dates in the "dd-MM-yy" pattern.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy");

    /**
     * Parses an array of strings containing task information and converts it into a {@link Task} object.
     *
     * @param taskInfo An array of strings representing the task details to be parsed.
     * @return A {@link Task} object created from the provided task information.
     */
    public abstract Task parse(String[] taskInfo);

}
