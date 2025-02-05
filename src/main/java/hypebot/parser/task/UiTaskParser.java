package hypebot.parser.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.exception.missing.MissingArgumentException;
import hypebot.exception.missing.MissingTaskNameException;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.ToDo;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code UiTaskParser} that parses all {@link Task}s
 * from user input taken in by {@link UiGuiMainWindow}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiTaskParser extends TaskParser {
    /**
     * Creates a new {@code UiTaskParser} containing a {@link UiDateTimeParser}.
     */
    public UiTaskParser() {
        super(new UiDateTimeParser());
    }

    /**
     * Takes in the full line entered by user and splits it into a {@link String} array containing
     * {@link Task} type & name, and optionally a due date or start time, end time.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@link String} array containing {@link Task} type & name, and optionally a due date
     *         or start time, end time.
     */
    @Override
    protected String[] splitLine(String line) {
        return line.split(" /");
    }

    /**
     * Takes in the full line entered by user and returns the corresponding {@code TaskType}
     * specified by the user.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@code TaskType} specified by the user.
     * @throws IllegalTaskTypeException If no accepted {@code TaskType} is parsed.
     */
    @Override
    protected TaskType extractTaskType(String line) throws IllegalTaskTypeException {
        String enteredTaskType = splitLine(line)[0].split(" ")[0].trim().toLowerCase();
        return switch(enteredTaskType) {
        case "td", "todo" -> TaskType.TODO;
        case "dl", "deadline" -> TaskType.DEADLINE;
        case "ev", "event" -> TaskType.EVENT;
        default -> throw new IllegalTaskTypeException(enteredTaskType);
        };
    }

    /**
     * Takes in the full line entered by user and returns the {@link String}
     * array containing the entered {@link Task}'s name separated by word.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@link String} array containing the {@link Task}'s name separated by word.
     * @throws MissingTaskNameException If no {@link Task} name is entered.
     */
    private String[] separateTaskNameFromLine(String line) throws MissingTaskNameException {
        String commandAndTaskNameString = splitLine(line)[0];
        String[] commandAndTaskName = commandAndTaskNameString.split(" ");

        String[] taskName = new String[commandAndTaskName.length - 1];
        System.arraycopy(commandAndTaskName, 1, taskName, 0, taskName.length);

        return taskName;
    }

    /**
     * Takes in the full line entered by user and returns the entered
     * {@link Task}'s name in {@link String} form using a {@link StringBuilder}.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@link String} name of the {@link Task} entered.
     * @throws MissingTaskNameException If no {@link Task} name is entered.
     */
    private String buildTaskName(String line) throws MissingTaskNameException {
        String[] taskNameWords = separateTaskNameFromLine(line);

        try {
            StringBuilder taskNameBuilder = new StringBuilder();
            for (int i = 0; i < taskNameWords.length - 1; i++) {
                taskNameBuilder.append(taskNameWords[i]).append(" ");
            }
            taskNameBuilder.append(taskNameWords[taskNameWords.length - 1]);
            return taskNameBuilder.toString().trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingTaskNameException();
        }
    }

    /**
     * Takes in the full line user enters and returns the {@link String} form
     * of the {@link Task} name entered.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@link String} form of the {@link Task} name entered.
     * @throws MissingTaskNameException If no {@link Task} name is entered.
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
     * Takes in the full line user enters and returns the {@link ToDo} entered.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link ToDo} instance with name specified by user.
     * @throws MissingTaskNameException If no name specified.
     */
    @Override
    public ToDo parseToDo(String fullCommand) throws MissingTaskNameException {
        String taskName = parseTaskName(fullCommand);
        return new ToDo(taskName);
    }

    /**
     * Takes in the full line user enters and returns the {@link Deadline} entered.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link Deadline} instance with name, due date specified by user.
     * @throws MissingArgumentException If any of name or due date not entered.
     * @throws DueDateParseException    If due date is not entered in an accepted format.
     * @throws DatePassedException      If due date has passed current date.
     */
    @Override
    public Deadline parseDeadline(String fullCommand)
            throws MissingArgumentException, DueDateParseException, DatePassedException {
        String taskName = parseTaskName(fullCommand);

        LocalDate dueDate = dateTimeParser.parseDueDate(fullCommand);

        return new Deadline(taskName, dueDate);
    }

    /**
     * Takes in the full line user enters and returns the {@link Event} entered.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link Event} task entered by user.
     * @throws MissingArgumentException    If any of task name, start time, or end time not entered.
     * @throws EventDateTimeParseException If event times not in an accepted format.
     * @throws DatePassedException         If {@link Event} has already concluded.
     */
    @Override
    public Event parseEvent(String fullCommand)
            throws MissingArgumentException, EventDateTimeParseException, DatePassedException {
        String taskName = parseTaskName(fullCommand);

        LocalDateTime[] eventTimes = dateTimeParser.parseEventTimes(fullCommand);

        LocalDateTime startTime = eventTimes[0];
        LocalDateTime endTime = eventTimes[1];

        return new Event(taskName, startTime, endTime);
    }

    /**
     * Takes in the full line user enters and returns the {@link Task} entered.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link Task} entered by user.
     * @throws MissingArgumentException      If any necessary {@link Task} fields not entered.
     * @throws HypeBotDateTimeParseException If any dates entered in not in an accepted format.
     * @throws DatePassedException           If {@link Task} date has already passed.
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
