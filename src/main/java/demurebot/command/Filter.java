package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.Deadline;
import demurebot.task.Event;
import demurebot.task.TaskList;
import demurebot.task.Todo;
import demurebot.ui.Ui;

/**
 * The Filter class is responsible for filtering the task list based on the type of task.
 */
public class Filter extends Command {
    private final String type;
    /**
     * Constructs a new FilterTodo command .
     */
    public Filter(String type) {
        this.type = type;
    }

    /**
     * Filters task list to only todos
     *
     * @param tasks TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @return List of todos.
     * @throws DemureBotException If the filter type is invalid.
     */
    public String execute(TaskList tasks, Ui ui) throws DemureBotException {
        return switch (type) {
        case "T" -> todoFilter(tasks, ui);
        case "D" -> deadlineFilter(tasks, ui);
        case "E" -> eventFilter(tasks, ui);
        default -> throw new DemureBotException("Invalid filter type");
        };
    }

    /**
     * Filters the task list to only todos.
     *
     * @param tasks TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @return List of todos.
     */
    public String todoFilter(TaskList tasks, Ui ui) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i) instanceof Todo) {
                result.append(tasks.getTask(i)).append("\n");
            }
        }
        return result.toString().isEmpty() ? ui.displayNoTasksFound() : result.toString();
    }

    /**
     * Filters the task list to only deadlines.
     *
     * @param tasks TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @return List of deadlines.
     */
    public String deadlineFilter(TaskList tasks, Ui ui) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i) instanceof Deadline) {
                result.append(tasks.getTask(i)).append("\n");
            }
        }
        return result.toString().isEmpty() ? ui.displayNoTasksFound() : result.toString();
    }

    /**
     * Filters the task list to only events.
     *
     * @param tasks TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @return List of events.
     */
    public String eventFilter(TaskList tasks, Ui ui) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i) instanceof Event) {
                result.append(tasks.getTask(i)).append("\n");
            }
        }
        return result.toString().isEmpty() ? ui.displayNoTasksFound() : result.toString();
    }
}
