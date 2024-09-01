package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

/**
 * UnmarkCommand unmarks a Task when executed.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand that unmarks a Task in the TaskList.
     *
     * @param index The index to unmark a Task.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks a Task when executed.
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            taskList.unmark(index);
            setMessage(new String[]{
                "I have unmarked the task!",
                "\t" + taskList.getTask(index).toString()
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
