package darwin.parser;

import darwin.exception.IllegalTaskArgumentException;
import darwin.exception.IllegalTaskTypeException;
import darwin.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TaskParser interface to parse task arguments into Task objects.
 */
public interface TaskParser {
    Task parse(String taskArgs) throws IllegalTaskArgumentException;
    Task parseFromDb(String taskStr) throws IllegalTaskTypeException, IllegalTaskArgumentException;

    default void handleTaskStatus(Task task, String taskStatus) throws IllegalTaskArgumentException {
        switch (taskStatus) {
        case "1" -> task.markDone();
        case "0" -> task.unmarkDone();
        default -> throw new IllegalTaskArgumentException("");
        }
    }

    default void throwIllegalTaskArgumentException() throws IllegalTaskArgumentException {
        throw new IllegalTaskArgumentException("Wrong task description, ensure that it follows the different task types");
    }

    default LocalDateTime stringToDateTime(String dateTimeStr) throws IllegalTaskArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throwIllegalTaskArgumentException();
            return null;
        }
    }

    default LocalDateTime dbStringToDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr);
    }
}
