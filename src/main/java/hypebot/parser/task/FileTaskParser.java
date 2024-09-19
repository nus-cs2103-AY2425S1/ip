package hypebot.parser.task;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalTaskStatusException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.main.HypeBot;
import hypebot.parser.datetime.FileDateTimeParser;
import hypebot.storage.StorageManager;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

/**
 * Represents the {@code FileTaskParser} associated with parsing {@code Task}s
 * encoded in a save {@link File} accessed by a{@link HypeBot}'s {@link StorageManager}.
 * <p>A child of {@link TaskParser}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class FileTaskParser extends TaskParser {
    /**
     * Creates a new {@code FileTaskParser} containing a {@link FileDateTimeParser}.
     */
    public FileTaskParser() {
        super(new FileDateTimeParser());
    }

    /**
     * Takes in the full {@link String} line of a save {@link File} containing
     * encoded {@link Task} information and returns the {@link String} array of all
     * information separated into type, completion status, name, and potential
     * date-related categories.
     *
     * @param line {@link String} line of a save {@link File}.
     * @return {@link String} array of all {@link Task} information separated by categories.
     */
    @Override
    protected String[] splitLine(String line) {
        return line.split(" , ");
    }

    /**
     * Takes in the full {@link String} line of a save {@link File} and returns
     * the integer in the {@link Task} completion status column.
     *
     * @param line {@link String} line of a save {@link File}.
     * @return Integer in the completion status column.
     * @throws IllegalTaskStatusException If completion status not indicated as an integer.
     */
    private int isCompleteAsANumber(String line) throws IllegalTaskStatusException {
        int taskCompletedArrayIdx = 1;
        String isCompleteMarker = splitLine(line)[taskCompletedArrayIdx];
        try {
            return Integer.parseInt(isCompleteMarker);
        } catch (NumberFormatException e) {
            throw new IllegalTaskStatusException(isCompleteMarker);
        }
    }

    /**
     * Takes in the integer in the {@link Task} completion status column of a line,
     * and verifies whether it is a valid indicator (0 for incomplete, 1 for complete).
     *
     * @param isCompleteNumber Integer in the {@link Task} completion status column.
     * @throws IllegalTaskStatusException If completion status is not a valid entry.
     */
    private void checkIsCompleteIsValidNumber(int isCompleteNumber) throws IllegalTaskStatusException {
        if (isCompleteNumber != 0 && isCompleteNumber != 1) {
            throw new IllegalTaskStatusException(String.valueOf(isCompleteNumber));
        }
    }

    /**
     * Takes in the {@link String} line of a save {@link File} and a {@link Task}
     * parsed using the details of this line, and {@code mark()}s the {@link Task}'s
     * completion status accordingly.
     *
     * @param line {@link String} line of a save {@link File}.
     * @param task {@link Task} parsed using the details of this line
     * @throws IllegalTaskStatusException If completion status not indicated as an integer
     *                                    or is an integer other than 0 and 1.
     */
    private void checkIsCompleted(String line, Task task) throws IllegalTaskStatusException {
        int isCompleteAsInteger = isCompleteAsANumber(line);

        checkIsCompleteIsValidNumber(isCompleteAsInteger);

        if (isCompleteAsInteger == 1) {
            task.mark();
        }
    }

    @Override
    protected String parseTaskName(String line) {
        int taskNameArrayIdx = 2;
        return splitLine(line)[taskNameArrayIdx];
    }

    @Override
    public ToDo parseToDo(String line) {
        String taskName = parseTaskName(line);
        return new ToDo(taskName);
    }

    private String extractDueDateString(String line) {
        int dueDateArrayIdx = 3;
        return splitLine(line)[dueDateArrayIdx];
    }

    @Override
    public Deadline parseDeadline(String line) throws DueDateParseException, IllegalArgumentException {
        String taskName = parseTaskName(line);
        String dueDateString = extractDueDateString(line);
        LocalDate dueDate = dateTimeParser.parseDueDate(dueDateString);
        return new Deadline(taskName, dueDate);
    }

    private String parseEventTimesStringFile(String line) {
        int startTimeArrayIdx = 3;
        int endTimeArrayIdx = 4;
        return splitLine(line)[startTimeArrayIdx] + "/" + splitLine(line)[endTimeArrayIdx];
    }

    @Override
    public Event parseEvent(String line) throws EventDateTimeParseException, DatePassedException {
        String taskName = parseTaskName(line);
        String eventTimesString = parseEventTimesStringFile(line);
        LocalDateTime[] eventTimes = dateTimeParser.parseEventTimes(eventTimesString);
        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];
        return new Event(taskName, startTime, endTime);
    }

    @Override
    protected TaskType extractTaskType(String line) throws IllegalTaskTypeException {
        int taskTypeArrayIdx = 0;
        String enteredTaskType = splitLine(line)[taskTypeArrayIdx];
        return switch(enteredTaskType) {
        case "T" -> TaskType.TODO;
        case "D" -> TaskType.DEADLINE;
        case "E" -> TaskType.EVENT;
        default -> throw new IllegalTaskTypeException(enteredTaskType);
        };
    }

    /**
     * Takes in a {@link String} representation of a {@link Task} saved on a {@link File},
     * and returns a {@link Task} with the corresponding details.
     *
     * @param line {@link String} form of a {@link Task} outlined in {@link File}.
     * @return {@link Task} with corresponding details.
     * @throws DatePassedException           If a {@link Deadline}'s due date has passed current
     *                                       date, or an {@link Event} has already concluded.
     * @throws HypeBotDateTimeParseException If {@link Deadline}'s due date or {@link Event} times
     *                                       encoded in an incorrect format.
     * @throws IllegalTaskStatusException    If a {@link Task}'s completion status is not an
     *                                       accepted value.
     * @throws IllegalTaskTypeException      If no accepted task type is detected.
     */
    @Override
    public Task parse(String line) throws DatePassedException, HypeBotDateTimeParseException,
            IllegalTaskStatusException, IllegalTaskTypeException {
        TaskType taskType = extractTaskType(line);
        Task newTask = switch(taskType) {
        case TODO -> parseToDo(line);
        case DEADLINE -> parseDeadline(line);
        case EVENT -> parseEvent(line);
        };
        checkIsCompleted(line, newTask);
        return newTask;
    }
}
