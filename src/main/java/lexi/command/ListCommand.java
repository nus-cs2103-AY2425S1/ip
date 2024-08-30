package lexi.command;

import java.util.ArrayList;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, this command displays all tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        ArrayList<Task> taskList = tasks.getTasks();
        if (taskList.isEmpty()) {
            throw new LexiException("You have no tasks in your list!");
        }
        ui.showListOfTasks(taskList);
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "LIST".
     */
    @Override
    public String getCommandName() {
        return "LIST";
    }
}
