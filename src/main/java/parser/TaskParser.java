package parser;

import socchat.SocchatException;
import socchat.task.Task;
import socchat.task.deadline.Deadline;
import socchat.task.event.Event;
import socchat.task.todo.Todo;

import java.time.LocalDate;

/**
 * The {@code TaskParser} class provides utility methods for parsing task-related
 * input strings into specific task objects such as {@link Todo}, {@link Deadline}, and {@link Event}.
 * It validates the input format and handles task-specific details like tags and deadlines.
 */
public class TaskParser {

    /**
     * Parses a "todo" task from the input string.
     * The expected format is "description -tag optionalTag".
     *
     * @param todoDetails the input string containing the todo task details
     * @return a {@link Todo} object based on the parsed input
     * @throws SocchatException if the input format is invalid or the description is empty
     */
    public static Task todoParser(String todoDetails) throws SocchatException {
            assert todoDetails != null;

            // todo format is [description, tag]
            String taskPattern = "-tag";
            String[] strToken = todoDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);

            if (todoDetails.contains("-tag")) {
                String tagName = getTag(strToken, 1);
                return new Todo(desc, tagName);
            }
            return new Todo(desc);
    }

    /**
     * Parses a "deadline" task from the input string.
     * The expected format is "description /by yyyy-MM-dd -tag optionalTag".
     *
     * @param deadlineDetails the input string containing the deadline task details
     * @return a {@link Deadline} object based on the parsed input
     * @throws SocchatException if the input format is invalid or the description is empty
     */
    public static Task deadlineParser(String deadlineDetails) throws SocchatException {
        try {
            assert deadlineDetails != null;

            // deadline format is [description, by, tag]
            String taskPattern = "/by |-tag";
            String[] strToken = deadlineDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);

            String by = strToken[1].trim();
            LocalDate formattedBy = DateParser.parseDate(by);

            if (deadlineDetails.contains("-tag")) {
                String tagName = getTag(strToken, 2);
                return new Deadline(desc, formattedBy, tagName);
            }

            return new Deadline(desc, formattedBy);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Deadline format: deadline <description> /by <deadline>");
        }
    }

    /**
     * Parses an "event" task from the input string.
     * The expected format is "description /from yyyy-MM-dd /to yyyy-MM-dd -tag optionalTag".
     *
     * @param eventDetails the input string containing the event task details
     * @return an {@link Event} object based on the parsed input
     * @throws SocchatException if the input format is invalid, the description is empty, or dates are not valid
     */
    public static Task eventParser(String eventDetails) throws SocchatException {
        try {
            assert eventDetails != null;

            // event format is [description, from, to, tag]
            String taskPattern = " /from |/to |-tag";
            String[] strToken = eventDetails.split(taskPattern);
            String desc = strToken[0].trim();
            checkEmptyDescription(desc);

            String from  = strToken[1].trim();
            String to = strToken[2].trim();
            LocalDate formattedFrom = DateParser.parseDate(from);
            LocalDate formattedTo = DateParser.parseDate(to);
            DateParser.checkEndDate(formattedFrom, formattedTo);

            if (eventDetails.contains("-tag")) {
                String tagName = getTag(strToken, 3);
                return new Event(desc, formattedFrom, formattedTo, tagName);
            }

            return new Event(desc, formattedFrom, formattedTo);
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        }
    }

    /**
     * Validates that the task description is not empty.
     *
     * @param input the task description
     * @throws SocchatException if the description is empty
     */
    public static void checkEmptyDescription(String input) throws SocchatException {
        if (input.isEmpty()) {
            throw new SocchatException("Description cannot be empty. Meow~");
        }
    }

    /**
     * Retrieves the task tag from the input string tokens.
     *
     * @param strToken the tokenized input string
     * @param strTokenIndex the index at which the tag is expected
     * @return the tag string, trimmed
     * @throws SocchatException if the tag is missing or empty
     */
    public static String getTag(String[] strToken, int strTokenIndex) throws SocchatException {
        try {
            return strToken[strTokenIndex].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Tag Name cannot be empty. Meow~");
        }
    }
}
