package kietwoforone.commands;

import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;
import kietwoforone.storage.Storage;

/**
 * Represent the command called when the user wants to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the task list.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTaskList());
    }

    /**
     * Returns a boolean to determine whether to close the chatbot.
     * True if the command closes the chatbot and false otherwise.
     *
     * @return Boolean to determine whether to close the chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the string representation of the ListCommand object.
     *
     * @return String representation of the ListCommand object.
     */
    @Override
    public String toString() {
        return "Tasks listed";
    }

}
