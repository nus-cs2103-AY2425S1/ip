package duck.commands;

import java.util.Comparator;

import duck.common.Message;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.parameter.By;
import duck.parameter.Target;
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
    private static final String PARAMETER_BY = "/by";
    private static final String INVALID_MISSING_PARAMETER_TARGET = "Missing /target parameter.";

    private static final String INVALID_MISSING_PARAMETER_BY = "Missing /by parameter.";
    private static final String INVALID_UNKNOWN_TARGET = "Unknown target '";

    /**
     * Constructs a SortCommand object with the specified message.
     *
     * @param message The message containing the target and criterion for sorting tasks.
     */
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
        Target target = getTarget(splitString);
        By by = getBy(splitString);

        sortRelevantTasks(tasks, storage, target, by);
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
    private Target getTarget(String... message) throws DuckException {
        for (int i = 0; i < message.length - 1; i++) {
            if (message[i].equals(PARAMETER_TARGET)) {
                return Target.fromKeyword(message[i + 1]);
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
    private By getBy(String... message) throws DuckException {
        for (int i = 0; i < message.length - 1; i++) {
            if (message[i].equals(PARAMETER_BY)) {
                return By.fromKeyword(message[i + 1]);
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
    private Comparator<Task> getComparator(Target target, By by) throws DuckException {
        switch (target) {
        case ALL:
            return getAllComparator(by);
        case TODO:
            return getToDoComparator(by);
        case DEADLINE:
            return getDeadlineComparator(by);
        case EVENT:
            return getEventComparator(by);
        default:
            throw new DuckException(INVALID_UNKNOWN_TARGET + target);
        }
    }

    private Comparator<Task> getAllComparator(By by) throws DuckException {
        switch (by) {
        case DESCRIPTION:
            return Comparator.comparing(Task::getDescription);
        case TYPE:
            return Comparator.comparing(task -> {
                if (task instanceof ToDo) {
                    return 0;
                } else if (task instanceof Deadline) {
                    return 1;
                } else if (task instanceof Event) {
                    return 2;
                }
                return 3; // Unknown task types come last
            });
        default:
            throw new DuckException(Message.INVALID_KEYWORD_BY_FOR_ALL);
        }
    }

    private Comparator<Task> getToDoComparator(By by) throws DuckException {
        if (by != By.DESCRIPTION) {
            throw new DuckException(Message.INVALID_KEYWORD_BY_FOR_TODO);
        }
        return Comparator.comparing(task -> ((ToDo) task).getDescription());
    }

    private Comparator<Task> getDeadlineComparator(By by) throws DuckException {
        switch (by) {
        case DESCRIPTION:
            return Comparator.comparing(task -> ((Deadline) task).getDescription());
        case DEADLINE:
            return Comparator.comparing(task -> ((Deadline) task).getBy());
        default:
            throw new DuckException(Message.INVALID_KEYWORD_BY_FOR_DEADLINE);
        }
    }

    private Comparator<Task> getEventComparator(By by) throws DuckException {
        switch (by) {
        case DESCRIPTION:
            return Comparator.comparing(task -> ((Event) task).getDescription());
        case START:
            return Comparator.comparing(task -> ((Event) task).getFrom());
        case END:
            return Comparator.comparing(task -> ((Event) task).getTo());
        default:
            throw new DuckException(Message.INVALID_KEYWORD_BY_FOR_EVENT);
        }
    }

    private void sortRelevantTasks(TaskList tasks, Storage storage, Target target, By by) throws DuckException {
        Comparator<Task> comp = getComparator(target, by);

        tasks.sortTasks(comp, target.getKeyword());
        storage.writeTasks(tasks);

        System.out.println(target.getKeyword() + " tasks sorted successfully by " + by.getKeyword() + "!");
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return {@code false} as the sort command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
