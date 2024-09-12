package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{
    private int taskNumber;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskNumber The number of the task to be deleted.
     */

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }


    /**
     * Deletes the task from the task list and updates the storage file.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory
     * @throws LightException if an error occurs during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        String reply = ui.beautifyMessage("Noted. I've removed this task:\n" + tasks.get(taskNumber) + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(taskNumber);
        taskListHistory.add(tasks.clone());
        String stringOfTask = TaskList.arrayToNumberedString(tasks);
        storage.write(stringOfTask);
        return reply;
    }
}
