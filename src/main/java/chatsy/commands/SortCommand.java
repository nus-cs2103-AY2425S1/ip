package chatsy.commands;

import chatsy.TaskManager;

/**
 * Represents a command to sort tasks.
 */
public class SortCommand extends Command {

    private final boolean isReverse;

    /**
     * Constructs a {@code SortCommand} with a flag for reverse sorting.
     *
     * @param arguments The sorting order arguments, such as "reverse".
     */
    public SortCommand(String arguments) {
        this.isReverse = arguments.equalsIgnoreCase("reverse");
    }

    @Override
    public String execute(TaskManager taskManager) {
        if (isReverse) {
            taskManager.sortDeadlinesReverseChronologically();
            return "Tasks sorted in reverse chronological order.";
        } else {
            taskManager.sortDeadlinesChronologically();
            return "Tasks sorted in chronological order.";
        }
    }
}
