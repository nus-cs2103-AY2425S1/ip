package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;

/**
 * The {@code SnoozeCommand} class handles the snoozing of tasks that are either deadlines or events.
 * It takes a task number as input, checks if the task is valid and scheduled,
 * and then snoozes the task if applicable. The snoozed task's updated information is saved,
 * and feedback is given to the user.
 */
public class SnoozeCommand extends Command {
    protected static final String WHITESPACE_DELIMITER = "\\s+";
    private String info;

    public SnoozeCommand(String info) {
        this.info = info;
    }
    /**
     * Executes the snooze command. It checks the validity of the task number and
     * ensures that the task is a scheduled task (either DEADLINE or EVENT).
     * If the task is valid, it is snoozed, and the updated task list is saved.
     *
     * @param tasks    The task list containing the tasks.
     * @param ui       The UI component responsible for interacting with the user.
     * @param storage  The storage used to save the updated task list.
     * @throws LictException If the task number is invalid, not a scheduled task, or cannot be snoozed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        String[] messageParts = info.split(WHITESPACE_DELIMITER, 2);
        String taskNum = messageParts[0].trim();
        int index;
        try {
            index = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            throw new LictException("Please enter a valid integer index. For eg. 'snooze 1'");
        }
        if (index < 0) {
            throw new LictException("Invalid task number. Task numbers should all be positive.");
        } else if (index >= tasks.size()) {
            throw new LictException("Invalid task number. There are only " + tasks.size() + " tasks in the list.");
        }
        Task t = tasks.get(index);
        if (!t.isScheduledTask()) {
            throw new LictException("Only DEADLINE or EVENT tasks can be snoozed.");
        }
        String newDateTime;
        if (messageParts.length != 2) {
            newDateTime = "";
        } else {
            newDateTime = messageParts[1].trim();
        }
        t.snoozeTask(ui, newDateTime);
        ui.hasSnoozedTask(t);
        storage.saveTasks(tasks);
    }
}
