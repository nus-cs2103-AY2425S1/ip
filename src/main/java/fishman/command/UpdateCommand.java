package fishman.command;

import java.time.LocalDateTime;

import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;



/**
 * Represent the command to update a task in the task list.
 * This command implements the Command interface and is for
 * updating a task in the task list and returns the confirmation message
 * that the task has been successfully updated to the user.
 */
public class UpdateCommand implements Command {
    private final int index;
    private final LocalDateTime newStartDateTime;
    private final LocalDateTime newEndDateTime;

    /**
     * Constructs a UpdateCommand with the new DateTime.
     * @param index The index of the task object to update.
     * @param newStartDateTime The new start date time to update.
     * @param newEndDateTime The new end date time to update.
     */
    public UpdateCommand(int index, LocalDateTime newStartDateTime, LocalDateTime newEndDateTime) {
        this.index = index;
        this.newStartDateTime = newStartDateTime;
        this.newEndDateTime = newEndDateTime;
    }

    /**
     * @inheritDoc
     *
     *      Updates the task in the task list and returns a confirmation message.
     *
     * @param tasks The tasks to be updated.
     * @param ui The ui instance to generate the confirmation message.
     * @return The confirmation message indicating the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.getTask(index);
        if (task instanceof Deadline) {
            if (newStartDateTime != null) {
                ((Deadline) task).setDeadlineDate(newStartDateTime);
            }
        } else if (task instanceof Event) {
            if (newStartDateTime != null) {
                ((Event) task).setEventStart(newStartDateTime);
            }
            if (newEndDateTime != null) {
                ((Event) task).setEventEnd(newEndDateTime);
            }
        }
        return ui.getUpdatedTaskMessage(task);
    }


}
