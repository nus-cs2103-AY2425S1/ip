package luffy;

import java.io.IOException;

/**
 * Represents a command that deletes a task into the list of tasks
 */
public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * This method adds an executable command to delete a
     * task from the task list by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        Task deletedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        try {
            taskStorage.saveToFile(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showDeletedTask(deletedTask, taskList);
    }
}
