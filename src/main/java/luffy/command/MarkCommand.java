package luffy.command;

import java.io.IOException;

import luffy.storage.Storage;
import luffy.task.Task;
import luffy.task.TaskList;
import luffy.ui.LuffyUI;


/**
 * Represents a command that marks a task as done
 */
public class MarkCommand extends Command {

    private final int markIndex;
    private final int[] multipleMarkIndex;

    /**
     * Constructor to create a command that marks a task as done
     * @param index the index of the task to be marked done
     */
    public MarkCommand(int index) {
        this.multipleMarkIndex = null;
        this.markIndex = index;
    }

    /**
     * Constructor to create a command that marks multiple tasks as done
     * @param indices the indices of the task to be marked done
     */
    public MarkCommand(int[] indices) {
        this.multipleMarkIndex = indices.clone();
        this.markIndex = -1;
    }

    /**
     * This method adds an executable command to mark a
     * task or tasks in the task list at a specific index by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        if (multipleMarkIndex == null) {
            if (markIndex >= 0 && markIndex < taskList.size()) {
                Task task = taskList.getTask(markIndex);
                task.markDone();
                try {
                    taskStorage.saveToFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                ui.showMarkedTask(task);
            } else {
                ui.showErrorMessage("Invalid task number.");
            }
        } else {
            for (int index : multipleMarkIndex) {
                if (index >= 0 && index < taskList.size()) {
                    Task task = taskList.getTask(index);
                    task.markDone();
                    try {
                        taskStorage.saveToFile(taskList);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    ui.showMarkedTask(task);
                } else {
                    ui.showErrorMessage("Invalid task number.");
                }
            }
        }
    }
}
