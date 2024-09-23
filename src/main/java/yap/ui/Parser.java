package yap.ui;

import yap.task.Deadline;
import yap.task.DeadlineException;
import yap.task.Event;
import yap.task.EventException;
import yap.task.FixedDurationTask;
import yap.task.FixedDurationTaskException;
import yap.task.ToDo;

/**
 * A class containing methods to create task objects from users' string input.
 */
public class Parser {

    /**
     * Converts a users' input to a ToDo task.
     *
     * @param input The users' input as a string.
     * @return A ToDo task.
     * @throws InputException If the input has a wrong format and there is no description of the task.
     */
    public static ToDo parseInputAsToDo(String input) throws InputException {
        String taskName = input.replace("todo ", "").trim();
        if (taskName.isBlank()) {
            throw new InputException("The todo has no name!");
        }
        return new ToDo(taskName);
    }

    /**
     * Converts a users' input to an Event task.
     *
     * @param input The users' input as a string.
     * @return An Event task.
     * @throws InputException If the input has a wrong format.
     */
    public static Event parseInputAsEvent(String input) throws InputException {
        String[] parts;
        String[] times;
        String description;
        String startTime;
        String endTime;
        try {
            parts = input.split(" /from ");
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new EventException(EventException.EventExceptionType.NOFROM);
        }
        try {
            times = parts[1].split(" /to ");
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new EventException(EventException.EventExceptionType.NOTO);
        }
        try {
            description = parts[0].replace("event ", "").trim();
            startTime = times[0].trim();
            endTime = times[1].trim();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new EventException();
        }
        return new Event(description, startTime, endTime);
    }

    /**
     * Converts a users' input to a Deadline task.
     *
     * @param input The users' input as a string.
     * @return A Deadline task.
     * @throws InputException If the input has a wrong format.
     */
    public static Deadline parseInputAsDeadline(String input) throws InputException {
        String[] parts;
        String description;
        String deadline;
        try {
            parts = input.split(" /by ");
            description = parts[0].replace("deadline ", "").trim();
            deadline = parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DeadlineException();
        }
        return new Deadline(description, deadline);
    }

    public static FixedDurationTask parseInputAsFixedDurationTask(String input) throws InputException {
        String[] parts;
        String description;
        int duration;
        try {
            parts = input.split(" /duration");
            description = parts[0].replace("duration", "").trim();
            System.out.println(parts[1].trim());
            duration = Integer.parseInt(parts[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            throw new FixedDurationTaskException();
        }
        return new FixedDurationTask(description, duration);
    }

    public static String getStringFromFindCommand(String findCommand) {
        return findCommand.replace("find ", "").trim();
    }
}
