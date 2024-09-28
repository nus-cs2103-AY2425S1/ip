package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

/**
 * MarkCommand marks a Task when executed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand that marks at the specified index.
     *
     * @param index The index in the TaskList to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a Task when executed.
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            taskList.mark(index);
            setMessage(new String[]{
                "Well done on completing the task!",
                "\t" + taskList.getTask(index).toString()
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
