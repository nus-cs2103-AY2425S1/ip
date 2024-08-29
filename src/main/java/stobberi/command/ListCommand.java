package stobberi.command;

import stobberi.components.TaskList;

/**
 * Represents a command to display all tasks in a {@link TaskList}.
 */
public class ListCommand extends Command {
    /**
     * The list of tasks to be displayed.
     */
    private final TaskList taskList;

    /**
     * Constructs a new {@code ListCommand} with the specified {@link TaskList}.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command by displaying all tasks in the {@link TaskList}.
     */
    @Override
    public void execute() {
        taskList.displayList();
    }
}
