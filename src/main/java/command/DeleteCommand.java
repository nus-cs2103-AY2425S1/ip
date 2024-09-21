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
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Creates a DeleteCommand object.
     *
     * @param taskNumber The number of the task to be deleted.
     */

    public DeleteCommand(String taskNumber) throws LightException {
        try {
            this.taskNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new LightException("The task number is invalid.");
        }
    }


    /**
     * Deletes the task from the task list and updates the storage file.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory The task list history.
     * @throws LightException if an error occurs during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        try {
            new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
            tasks.remove(taskNumber);
            taskListHistory.add(tasks.clone());
            String stringOfTask = TaskList.arrayToNumberedString(tasks);
            storage.write(stringOfTask);
            String reply = "5..4..3..2..1\nI've deleted the task:\n"
                    + ui.beautifyMessage(stringOfTask);
            return reply;
        } catch (IndexOutOfBoundsException e) {
            throw new LightException("The task number is out of range.");
        }
    }
}
