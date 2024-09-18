package lebron;

import java.time.LocalDate;

/**
 * Represents a command to reschedule (snooze) a task to a new date.
 * This command updates the date of a specified task in the task list,
 * saves the updated list to storage, and returns a confirmation message.
 */
public class SnoozeCommand extends Command {
    private int index;
    private LocalDate newDate;

    /**
     * Constructs a SnoozeCommand with the specified index of the task to be rescheduled
     * and the new date to which the task should be snoozed.
     *
     * @param index The index of the task in the task list to be rescheduled.
     * @param newDate The new date to which the task should be rescheduled.
     */
    public SnoozeCommand(int index, LocalDate newDate) {
        this.index = index;
        this.newDate = newDate;
    }

    /**
     * Executes the SnoozeCommand by rescheduling the task at the specified index to the new date.
     * The task list is updated, the storage is saved, and a confirmation message is returned.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object used to save the updated task list.
     * @return A string message confirming that the task has been rescheduled.
     * @throws LeBronException If there is an error during task rescheduling.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        taskList.rescheduleTask(index, newDate);
        Task task = taskList.getTask(index);
        String message = ui.showTaskRescheduled(task);
        storage.saveTasks(taskList);
        return message;
    }
}
