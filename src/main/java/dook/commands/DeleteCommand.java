package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int taskNumber;
    private String removedTaskString = "Noted. I've removed this task";

    /**
     * Creates a DeleteCommand.
     *
     * @param taskNumber The 1-based index of the task to be deleted from the TaskList.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand, removing the specified task from the TaskList.
     * The task number is used to identify which task to delete.
     *
     * @param tasks The TaskList from which the task will be removed.
     * @param ui The Ui object that handles user interactions.
     * @param storage The Storage object that handles saving the updated TaskList.
     * @return A confirmation message that the task has been deleted.
     * @throws DookException If the task number is invalid or out of bounds.
     * @throws IOException If an I/O error occurs while saving the updated TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        Task removed = tasks.delete(taskNumber - 1);
        storage.write(tasks);

        return printMessages(tasks, ui, removed);
    }

    private String printMessages(TaskList tasks, Ui ui, Task removed) {
        ui.separate();
        ui.showMessage(removedTaskString);
        ui.showMessage(removed.toString());
        String numOfTasksLeft;
        if (tasks.numOfTasks() == 1) {
            numOfTasksLeft = String.format("Now you have %d task in the list", tasks.numOfTasks());
            ui.showMessage(numOfTasksLeft);
        } else {
            numOfTasksLeft = String.format("Now you have %d tasks in the list", tasks.numOfTasks());
            ui.showMessage(numOfTasksLeft);
        }
        ui.separate();

        return removedTaskString + "\n" + removed.toString() + "\n" + numOfTasksLeft;
    }
}
