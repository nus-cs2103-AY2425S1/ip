package duck.commands;

import java.util.Comparator;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.storage.Storage;
import duck.ui.Ui;


/**
 * Represents a command to sort tasks in the task list.
 * When executed, it sorts the tasks based on the specified target and criterion and updates the storage.
 *
 */
public class SortCommand extends Command {

    private static final String ERROR_MESSAGE_SORT_COMMAND = "Invalid sort command format.";
    private static final String PARAMETER_TARGET = "/target";
    private static final String INVALID_MISSING_PARAMETER_TARGET = "Missing /target parameter.";
    private static final String PARAMETER_BY = "/by";
    private static final String INVALID_MISSING_PARAMETER_BY = "Missing /by parameter.";
    private static final String PARAMETER_TARGET_KEYWORD_ALL = "all";
    private static final String PARAMETER_TARGET_KEYWORD_TODO = "todo";
    private static final String PARAMETER_TARGET_KEYWORD_DEADLINE = "deadline";
    private static final String PARAMETER_TARGET_KEYWORD_EVENT = "event";
    private static final String INVALID_UNKNOWN_TARGET = "Unknown target '";
    private static final String INVALID_KEYWORD_BY_FOR_EVENT =
            "For target 'event', you can only sort by 'description', 'start', or 'end'.";
    private static final String INVALID_KEYWORD_BY_FOR_DEADLINE =
            "For target 'deadline', you can only sort by 'description' or 'deadline'.";
    private static final String INVALID_KEYWORD_BY_FOR_ALL =
            "For target 'all', you can only sort by 'description' or 'type'.";
    private static final String INVALID_KEYWORD_BY_FOR_TODO = "For target 'todo', you can only sort by 'description'.";
    private static final String PARAMETER_BY_KEYWORD_DESCRIPTION = "description";
    private static final String PARAMETER_BY_KEYWORD_TYPE = "type";
    private static final String PARAMETER_BY_KEYWORD_START = "start";
    private static final String PARAMETER_BY_KEYWORD_END = "end";
    private static final String PARAMETER_BY_KEYWORD_DEADLINE = "deadline";
    private static final String MESSAGE_SORT_COMMAND = " tasks sorted successfully by ";

    public SortCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by sorting the tasks in the task list.
     *
     * @param tasks The list of tasks to be manipulated by the command.
     * @param storage The storage system for saving and loading tasks.
     * @param ui The user interface for displaying messages to the user.
     * @throws DuckException If an error occurs during sort.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        String[] splitString = splitMessage(message);
        validateMessage(splitString);

        // Extract keywords of the target and by parameters from the input message
        String target = getTarget(splitString);
        String by = getBy(splitString);

        sortRelevantTasks(tasks, storage, target, by);
    }

    private void sortRelevantTasks(TaskList tasks, Storage storage, String target, String by) throws DuckException {
        Comparator<Task> comp = getComparator(target, by);

        tasks.sortTasks(comp, target);
        storage.writeTasks(tasks);

        System.out.println(target + MESSAGE_SORT_COMMAND + by + "!");
    }

    /**
     * Parses the input message and splits it into an array of strings.
     * The method normalizes the input by converting it to lowercase and trimming extra spaces.
     *
     * @param input The raw input string from the user.
     * @return A string array representing the parsed components of the input message.
     */
    public String[] splitMessage(String input) {

        String normalizedInput = input.toLowerCase().trim();
        String[] parsedMessage = normalizedInput.split("\\s+");

        return parsedMessage;
    }

    /**
     * Validates the format of the input message.
     *
     * @param message The parsed input message as a string array.
     * @throws DuckException If the message format is invalid.
     */
    private void validateMessage(String... message) throws DuckException {
        if (message.length < 4) {
            throw new DuckException(ERROR_MESSAGE_SORT_COMMAND);
        }
    }

    /**
     * Extracts the /target parameter from the input message.
     *
     * @param message The parsed input message as a string array.
     * @return The target specified in the input message.
     * @throws DuckException If the target is missing or invalid.
     */
    private String getTarget(String... message) throws DuckException {
        for (int i = 0; i < message.length - 1; i++) {
            if (message[i].equals(PARAMETER_TARGET)) {
                return message[i + 1];
            }
        }
        throw new DuckException(INVALID_MISSING_PARAMETER_TARGET);
    }

    /**
     * Extracts the /by parameter from the input message.
     *
     * @param message The parsed input message as a string array.
     * @return The sorting criterion specified in the input message.
     * @throws DuckException If the sorting criterion is missing or invalid.
     */
    private String getBy(String... message) throws DuckException {
        for (int i = 0; i < message.length - 1; i++) {
            if (message[i].equals(PARAMETER_BY)) {
                return message[i + 1];
            }
        }
        throw new DuckException(INVALID_MISSING_PARAMETER_BY);
    }

    /**
     * Returns the appropriate comparator based on the /target and /by parameters.
     *
     * @param target The target of the sort command (e.g., "all", "todo", "deadline", "event").
     * @param by The sorting criterion (e.g., "description", "type", "deadline", "start", "end").
     * @return A comparator for sorting tasks based on the specified target and criterion.
     * @throws DuckException If the target and criterion combination is invalid.
     */
    private Comparator<Task> getComparator(String target, String by) throws DuckException {
        switch (target) {
        case PARAMETER_TARGET_KEYWORD_ALL:
            return getAllComparator(by);
        case PARAMETER_TARGET_KEYWORD_TODO:
            return getToDoComparator(by);
        case PARAMETER_TARGET_KEYWORD_DEADLINE:
            return getDeadlineComparator(by);
        case PARAMETER_TARGET_KEYWORD_EVENT:
            return getEventComparator(by);
        default:
            throw new DuckException(INVALID_UNKNOWN_TARGET + target);
        }
    }

    private static Comparator<Task> getToDoComparator(String by) throws DuckException {
        if (!by.equals(PARAMETER_BY_KEYWORD_DESCRIPTION)) {
            throw new DuckException(INVALID_KEYWORD_BY_FOR_TODO);
        }
        return Comparator.comparing(task -> ((ToDo) task).getDescription());
    }

    /**
     * Returns a comparator for sorting all tasks, either by description or type (todo, deadline, event).
     *
     * @param by The sorting criterion (either "description" or "type").
     * @return The comparator for sorting all tasks.
     * @throws DuckException If the sorting criterion is invalid.
     */
    private Comparator<Task> getAllComparator(String by) throws DuckException {
        if (by.equals(PARAMETER_BY_KEYWORD_DESCRIPTION)) {
            return Comparator.comparing(Task::getDescription);
        } else if (by.equals(PARAMETER_BY_KEYWORD_TYPE)) {
            return Comparator.comparing(task -> {
                if (task instanceof ToDo) {
                    return 0;
                }
                if (task instanceof Deadline) {
                    return 1;
                }
                if (task instanceof Event) {
                    return 2;
                }
                return 3; // Unknown task types come last
            });
        } else {
            throw new DuckException(INVALID_KEYWORD_BY_FOR_ALL);
        }
    }

    /**
     * Returns a comparator for sorting deadline tasks, either by description or deadline.
     *
     * @param by The sorting criterion (either "description" or "deadline").
     * @return The comparator for sorting deadline tasks.
     * @throws DuckException If the sorting criterion is invalid.
     */
    private Comparator<Task> getDeadlineComparator(String by) throws DuckException {
        if (by.equals(PARAMETER_BY_KEYWORD_DESCRIPTION)) {
            return Comparator.comparing(task -> ((Deadline) task).getDescription());
        } else if (by.equals(PARAMETER_BY_KEYWORD_DEADLINE)) {
            return Comparator.comparing(task -> ((Deadline) task).getBy());
        } else {
            throw new DuckException(INVALID_KEYWORD_BY_FOR_DEADLINE);
        }
    }

    /**
     * Returns a comparator for sorting event tasks, either by description, start time, or end time.
     *
     * @param by The sorting criterion (either "description", "start", or "end").
     * @return The comparator for sorting event tasks.
     * @throws DuckException If the sorting criterion is invalid.
     */
    private Comparator<Task> getEventComparator(String by) throws DuckException {
        // CHECKSTYLE.OFF: Indentation
        return switch (by) {
            case PARAMETER_BY_KEYWORD_DESCRIPTION -> Comparator.comparing(task -> ((Event) task).getDescription());
            case PARAMETER_BY_KEYWORD_START -> Comparator.comparing(task -> ((Event) task).getFrom());
            case PARAMETER_BY_KEYWORD_END -> Comparator.comparing(task -> ((Event) task).getTo());
            default ->
                    throw new DuckException(INVALID_KEYWORD_BY_FOR_EVENT);
        };
        // CHECKSTYLE.ON: Indentation
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
