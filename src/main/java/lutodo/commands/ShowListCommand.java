package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

/**
 * Represents the command of showing the task list.
 */
public class ShowListCommand extends Command{

    /**
     * Shows the task list to the user.
     *
     * @param tasks The TaskList object that is to be shown.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any task now :)");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("Tips: Tasks marked as [X] are already completed :)");
    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "ShowListCommand";
    }
}
