package chatsy.commands;

import chatsy.TaskManager;

/**
 * Handles the "sort" command which sorts tasks based on deadlines.
 * Tasks can be sorted either in chronological or reverse chronological order.
 */
public class SortCommand extends Command {

    private final boolean isReverse;

    /**
     * Constructs a {@code SortCommand} with a flag indicating if sorting should be in reverse order.
     *
     * @param arguments The sorting order argument. If the argument is "reverse", tasks will be sorted
     *                  in reverse chronological order.
     */
    public SortCommand(String arguments) {
        this.isReverse = arguments.equalsIgnoreCase("reverse");
    }

    /**
     * Executes the sort command by sorting the tasks based on deadlines.
     *
     * @param taskManager The task manager where tasks are sorted.
     * @return A string response indicating the sorting order.
     */
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
