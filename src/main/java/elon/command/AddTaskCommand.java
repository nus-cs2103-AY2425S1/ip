package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.Task;
import elon.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private Task task;

    /**
     * Creates an AddTaskCommand with the specified task.
     *
     * @param task the task to be added to the TaskList
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command by adding the task to the task list, saving the updated list, and
     * returning a message from the Ui.
     *
     * @param list the task list to which the task will be added
     * @param ui the user interface to interact with the user
     * @param storage the storage to save the updated task list
     * @return a string representing the result of adding the task
     * @throws IOException if an error occurs while saving the task list to storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
        list.addTask(this.task);
        storage.saveFile(list);
        return ui.addTask(task, list);
    }
}
