package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to snooze (postpone) a task in the task list to a new date and time.
 */
public class SnoozeTaskCommand extends Command {
    private int index;
    private LocalDateTime newDate;

    /**
     * Creates a SnoozeTaskCommand with the specified task index and new date to postpone the task to.
     *
     * @param index the index of the task to be snoozed in the task list
     * @param newDate the new date and time to postpone the task to
     */
    public SnoozeTaskCommand(int index, LocalDateTime newDate) {
        this.index = index;
        this.newDate = newDate;
    }

    /**
     * Executes the snooze task command by postponing the task at the specified index to a new date and time,
     * saving the updated task list, and returning a message from the Ui.
     * If the index is invalid, it will return an error message.
     *
     * @param list the task list containing the task to be snoozed
     * @param ui the user interface to interact with the user
     * @param storage the storage to save the updated task list
     * @return a string representing the result of snoozing the task or an error message if the index is invalid
     * @throws Exception if any error occurs during the execution or saving process
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws Exception {
        if (index < 0 || index >= list.listSize()) {
            return ui.showInvalidIndex();
        }
        list.snooze(index, newDate);
        storage.saveFile(list);
        return ui.snoozeTask(list.getTask(index));
    }
}
