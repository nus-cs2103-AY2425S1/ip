package luffy;

import java.io.IOException;

/**
 * Represents a command that marks a task as undone
 */
public class UnmarkCommand extends Command {

    int markIndex;

    public UnmarkCommand(int index) {
        this.markIndex = index;
    }

    /**
     * This method adds an executable command to unmark a
     * task in the task list at a specific index by the UI
     *
     * @param ui user interface for Chat Bot
     * @param taskStorage storage location for file
     * @param taskList array list of existing tasks
     */
    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {

        if (markIndex >= 0 && markIndex < taskList.size()) {
            Task task = taskList.getTask(markIndex);
            task.markUndone();
            try {
                taskStorage.saveToFile(taskList);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            ui.showUnmarkedTask(task);
        } else {
            ui.showErrorMessage("Invalid task number.");
        }
    }

}
