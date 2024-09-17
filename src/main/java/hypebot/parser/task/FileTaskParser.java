package hypebot.parser.task;

import hypebot.exception.illegal.IllegalTaskStatusException;
import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileTaskParser extends TaskParser {
    public FileTaskParser() {
        super(new FileDateTimeParser());
    }

    @Override
    protected String[] splitLine(String line) {
        return line.split(" , ");
    }

    private int isCompleteAsANumber(String line) throws IllegalTaskStatusException {
        String isCompleteMarker = splitLine(line)[taskCompletedArrayIdx];
        try {
            return Integer.parseInt(isCompleteMarker);
        } catch (NumberFormatException e) {
            throw new IllegalTaskStatusException(isCompleteMarker);
        }
    }

    private void checkIsCompleteIsValidNumber(int isCompleteNumber) throws IllegalTaskStatusException {
        if (isCompleteNumber != 0 && isCompleteNumber != 1) {
            throw new IllegalTaskStatusException(String.valueOf(isCompleteNumber));
        }
    }

    private void checkIsCompleted(String line, Task task) throws IllegalTaskStatusException {
        int isCompleteAsInteger = isCompleteAsANumber(line);

        checkIsCompleteIsValidNumber(isCompleteAsInteger);

        if (isCompleteAsInteger == 1) {
            task.mark();
        }
    }

    @Override
    protected String parseTaskName(String line) {
        return splitLine(line)[taskNameArrayIdx];
    }

    /**
     * Takes in a String representation of a ToDo task saved on a file,
     * and returns a ToDo task with the corresponding details.
     *
     * @param line String form of a ToDo task outlined in file.
     * @return ToDo task with corresponding details
     */
    @Override
    protected ToDo parseToDo(String line) {
        String taskName = parseTaskName(line);
        return new ToDo(taskName);
    }

    private String extractDueDateString(String line) {
        return splitLine(line)[dueDateArrayIdx];
    }

    /**
     * Takes in a String representation of a Deadline task saved on a file,
     * and returns a Deadline task with the corresponding details.
     *
     * @param line String form of a Deadline task outlined in file.
     * @return Deadline task with corresponding details
     * @throws DueDateParseException If due date encoded in an incorrect format.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    @Override
    protected Deadline parseDeadline(String line) throws DueDateParseException, IllegalArgumentException {
        String taskName = parseTaskName(line);
        String dueDateString = extractDueDateString(line);
        LocalDate dueDate = dateTimeParser.parseDueDate(dueDateString);
        return new Deadline(taskName, dueDate);
    }

    private String parseEventTimesStringFile(String line) {
        return splitLine(line)[startTimeArrayIdx] + "/" + splitLine(line)[endTimeArrayIdx];
    }

    /**
     * Takes in a String representation of an Event task saved on a file,
     * and returns an Event task with the corresponding details.
     *
     * @param line String form of an Event task outlined in file.
     * @return Event task with corresponding details.
     * @throws EventDateTimeParseException If event times encoded in an incorrect format.
     * @throws DatePassedException If event has already concluded.
     */
    @Override
    protected Event parseEvent(String line) throws EventDateTimeParseException, DatePassedException {
        String taskName = parseTaskName(line);
        String eventTimesString = parseEventTimesStringFile(line);
        LocalDateTime[] eventTimes = dateTimeParser.parseEventTimes(eventTimesString);
        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];
        return new Event(taskName, startTime, endTime);
    }

    @Override
    protected TaskType extractTaskType(String line) throws IllegalTaskTypeException {
        String enteredTaskType = splitLine(line)[taskTypeArrayIdx];
        return switch(enteredTaskType) {
            case "T" -> TaskType.TODO;
            case "D" -> TaskType.DEADLINE;
            case "E" -> TaskType.EVENT;
            default -> throw new IllegalTaskTypeException(enteredTaskType);
        };
    }

    /**
     * Takes in a String representation of a Task saved on a file,
     * and returns a Task with the corresponding details
     *
     * @param line String form of a Task outlined in file.
     * @return Task with corresponding details.
     * @throws HypeBotDateTimeParseException If Deadline's due date or Event times
     *                                    encoded in an incorrect format.
     * @throws DatePassedException If a Deadline's due date has passed current date,
     *                                  or an Event has already concluded.
     */
    @Override
    public Task parse(String line) throws IllegalTaskTypeException, IllegalTaskStatusException,
            HypeBotDateTimeParseException, DatePassedException {
        TaskType taskType = extractTaskType(line);
        Task newTask = switch (taskType) {
            case TODO -> parseToDo(line);
            case DEADLINE -> parseDeadline(line);
            case EVENT -> parseEvent(line);
        };
        checkIsCompleted(line, newTask);
        return newTask;
    }
}
