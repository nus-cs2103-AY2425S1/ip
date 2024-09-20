package barcus.command;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to find tasks with a specified substring in their description or tags.
 */
public class FindCommand extends Command {
    private String toFind;

    /**
     * Constructs a FindCommand to search for tasks containing the specified string in their description or tag.
     *
     * @param toFind the substring to search for in task descriptions and tags
     */
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Executes the command by searching for tasks in the task list that contain the specified substring
     * and displaying the matching tasks.
     *
     * @param tasks the task list to search through
     * @param storage the storage object (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        TaskList tasksSubstring = tasks.findTask(toFind);
        if (tasksSubstring.getLength() == 0) {
            output = "No tasks found with that in the description or tags!";
        } else {
            output = "Here are the matching tasks!\n" + tasksSubstring.getTaskListDisplay();
        }
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as finding tasks does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
