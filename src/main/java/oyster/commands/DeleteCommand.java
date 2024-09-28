package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

/**
 * DeleteCommand that deletes a Task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand that deletes at the specified index.
     *
     * @param index The index in the TaskList to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a Task when executed.
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            String deletedTask = taskList.pop(index).toString();
            setMessage(new String[]{
                "I have deleted the task!",
                "\t" + deletedTask,
                String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
