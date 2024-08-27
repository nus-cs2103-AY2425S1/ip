package task;

import exception.IllegalTaskArgumentException;
import exception.IllegalTaskTypeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface TaskParser {
    Task parse(String taskArgs) throws IllegalTaskArgumentException;
    Task parseFromDb(String taskStr) throws IllegalTaskTypeException, IllegalTaskArgumentException;
    default void handleTaskStatus(Task task, String taskStatus) throws IllegalTaskArgumentException {
        switch (taskStatus) {
            case "1" -> task.markDone();
            case "0" -> task.unmarkDone();
            default -> throw new IllegalTaskArgumentException();
        }
    }
    default LocalDateTime stringToDateTime(String dateTimeStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
    default LocalDateTime dbStringToDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr);
    }
}
