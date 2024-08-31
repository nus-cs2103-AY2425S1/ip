package luffy;
import java.io.IOException;

/**
 * Represents a command that adds a task into the list of tasks
 */
public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * This method adds an executable command to add a
     * task into the task list by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        taskList.addTask(this.task);
        try {
            taskStorage.saveToFile(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showAddedTask(this.task, taskList);
    }
}
