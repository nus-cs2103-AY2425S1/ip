package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that marks and unmarks a task as done.
 */
public class MarkingCommands extends Command {

    private int taskNumber;
    private boolean isMarked;

    private String markDoneMessage = "Nice! I've marked this task as done:";
    private String unmarkDoneMessage = "Ok, I've marked this task as not done yet:";

    /**
     * Creates a MarkingCommands object to mark or unmark a task.
     *
     * @param taskNumber The index of the task to be marked or unmarked.
     * @param isMarked A boolean indicating whether to mark the task as done or not done (True or false respectively).
     */
    public MarkingCommands(int taskNumber, boolean isMarked) {
        this.taskNumber = taskNumber;
        this.isMarked = isMarked;
    }

    /**
     * Executes the MarkingCommands, marking or unmarking the specified task.
     * Updates the task's status and saves the TaskList.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui object used to handle user interactions and display task status updates.
     * @param storage The Storage object that handles saving the TaskList.
     * @return A message indicating the updated status of the task, or an exception message if an error occurs.
     * @throws DookException If the task number is invalid or an error occurs during task operations.
     * @throws IOException If an I/O error occurs while saving the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {

        assert taskNumber >= 0 && taskNumber < tasks.numOfTasks() : "Task number out of range: " + taskNumber;

        Task task = tasks.getTask(taskNumber - 1);

        if (this.isMarked) {
            task.markAsDone();
        } else {
            task.unmark();
        }
        storage.write(tasks);

        return printMessages(ui, task);
    }

    private String printMessages(Ui ui, Task task) {
        ui.separate();
        String message;
        if (this.isMarked) {
            message = markDoneMessage;
            ui.showMessage(message);
        } else {
            message = unmarkDoneMessage;
            ui.showMessage(message);
        }
        message = message.concat("\n" + task.toString());
        ui.showMessage(task.toString());
        ui.separate();
        return message;
    }
}
