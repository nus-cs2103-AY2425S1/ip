package hypebot.parser.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.exception.missing.MissingArgumentException;
import hypebot.exception.missing.MissingTaskNameException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;

/**
 * Represents the UiTaskParser that parses all tasks from user input.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class UiTaskParser extends TaskParser {
    public UiTaskParser() {
        super(new UiDateTimeParser());
    }

    @Override
    protected String[] splitLine(String line) {
        return line.split(" /");
    }

    @Override
    protected TaskType extractTaskType(String line) throws IllegalTaskTypeException {
        String enteredTaskType = splitLine(line)[0].trim().toLowerCase();
        return switch(enteredTaskType) {
        case "td", "todo" -> TaskType.TODO;
        case "dl", "deadline" -> TaskType.DEADLINE;
        case "ev", "event" -> TaskType.EVENT;
        default -> throw new IllegalTaskTypeException(enteredTaskType);
        };
    }

    private String[] separateTaskNameFromLine(String line) {
        String commandAndTaskNameString = splitLine(line)[0];
        String[] commandAndTaskName = commandAndTaskNameString.split(" ");

        String[] taskName = new String[commandAndTaskName.length - 1];
        System.arraycopy(commandAndTaskName, 1, taskName, 0, taskName.length);

        return taskName;
    }

    private String buildTaskName(String line) {
        String[] taskNameWords = separateTaskNameFromLine(line);

        StringBuilder taskNameBuilder = new StringBuilder();
        for (int i = 0; i < taskNameWords.length - 1; i++) {
            taskNameBuilder.append(taskNameWords[i]).append(" ");
        }
        taskNameBuilder.append(taskNameWords[taskNameWords.length - 1]);

        return taskNameBuilder.toString().trim();
    }

    /**
     * Takes in the full line user enters and returns the {@code String} form
     * of the {@code Task} name entered.
     *
     * @param line Full {@code String} command entered by user.
     * @return {@code String} form of the {@code Task} name entered.
     * @throws MissingTaskNameException If no {@code Task} name is entered.
     */
    @Override
    protected String parseTaskName(String line) throws MissingTaskNameException {
        String taskName = buildTaskName(line);
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException();
        }
        return taskName;
    }

    /**
     * Takes in the full line user enters and returns the {@code ToDo} entered.
     *
     * @param fullCommand Full {@code String} command entered by user.
     * @return {@code ToDo} instance with name specified by user.
     * @throws MissingTaskNameException If no name specified.
     */
    @Override
    protected ToDo parseToDo(String fullCommand) throws MissingTaskNameException {
        String taskName = parseTaskName(fullCommand);
        return new ToDo(taskName);
    }

    /**
     * Takes in the full line user enters and returns the {@code Deadline} entered.
     *
     * @param fullCommand Full {@code String} command entered by user.
     * @return {@code Deadline} instance with name, due date specified by user.
     * @throws MissingArgumentException If any of name or due date not entered.
     * @throws DueDateParseException If due date is not entered in yyyy-MM-dd format.
     * @throws DatePassedException If due date has passed current date.
     */
    @Override
    protected Deadline parseDeadline(String fullCommand)
            throws MissingArgumentException, DueDateParseException, DatePassedException {
        String taskName = parseTaskName(fullCommand);

        LocalDate dueDate = dateTimeParser.parseDueDate(fullCommand);

        return new Deadline(taskName, dueDate);
    }

    /**
     * Takes in the full line user enters and returns the {@code Event} entered.
     *
     * @param fullCommand Full String command entered by user.
     * @return Event task entered by user.
     * @throws MissingArgumentException If any of task name, start time, or end time not entered.
     * @throws EventDateTimeParseException If event times not entered in yyyy-MM-dd HHmm format.
     * @throws DatePassedException If event has already concluded.
     */
    @Override
    protected Event parseEvent(String fullCommand)
            throws MissingArgumentException, EventDateTimeParseException, DatePassedException {
        String taskName = parseTaskName(fullCommand);

        LocalDateTime[] eventTimes = dateTimeParser.parseEventTimes(fullCommand);

        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];

        return new Event(taskName, startTime, endTime);
    }

    /**
     * Takes in the full line user enters and returns the Task entered.
     *
     * @param fullCommand Full String command entered by user.
     * @return Task entered by user.
     * @throws MissingArgumentException If any necessary Task fields not entered.
     * @throws HypeBotDateTimeParseException If any dates entered in not in the specified format.
     * @throws DatePassedException If Task date has already passed.
     */
    @Override
    public Task parse(String fullCommand)
            throws MissingArgumentException, HypeBotDateTimeParseException, DatePassedException {
        TaskType taskType = extractTaskType(fullCommand);
        return switch(taskType) {
        case TODO -> parseToDo(fullCommand);
        case DEADLINE -> parseDeadline(fullCommand);
        case EVENT -> parseEvent(fullCommand);
        };
    }
}
