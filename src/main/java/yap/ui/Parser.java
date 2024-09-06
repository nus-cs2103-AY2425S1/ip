package yap.ui;

import yap.task.Deadline;
import yap.task.DeadlineException;
import yap.task.Event;
import yap.task.EventException;
import yap.task.ToDo;

public class Parser {
    public static ToDo parseInputAsToDo(String input) throws InputException {
        String taskName = input.replace("todo ", "").trim();
        if (taskName.isBlank()) {
            throw new InputException("The todo has no name!");
        }
        return new ToDo(taskName);
    }
    public static Event parseInputAsEvent(String input) throws InputException {
        String[] parts, times;
        String description, startTime, endTime;
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

    public static Deadline parseInputAsDeadline(String input) throws InputException {
        String[] parts;
        String description, deadline;
        try {
            parts = input.split(" /by ");
            description = parts[0].replace("deadline ", "").trim();
            deadline = parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DeadlineException();
        }
        return new Deadline(description, deadline);
    }

    public static String getStringFromFindCommand(String findCommand) {
        return findCommand.replace("find ", "").trim();
    }
}
