package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents a command to update an existing task in the task list.
 */
public class UpdateCommand extends Command {
    private final int taskIndex;
    private final String newDetails;
    private final String fieldType;
    private static final String DESCRIPTION_FIELD = "description";
    private static final String DATE_FIELD = "date";

    /**
     * Constructs an UpdateCommand.
     *
     * @param taskIndex  The index of the task to update.
     * @param newDetails The new details to update the task with.
     * @param fieldType  The type of field to update (e.g., "description", "date").
     */
    public UpdateCommand(int taskIndex, String newDetails, String fieldType) {
        this.taskIndex = taskIndex - 1;
        this.newDetails = newDetails;
        this.fieldType = fieldType;
        assert taskIndex > 0 : "Task index must be positive";
    }

    /**
     * Executes the update command, updating the specified field of the task and saving changes.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display the updated task.
     * @param storage The storage to save the updated task list.
     * @return The UI output after task update.
     * @throws ChatBuddyException If the task index is out of range or invalid field type.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        validateTaskList(tasks);
        Task task = tasks.getTask(taskIndex);

        updateTaskField(task);

        storage.saveTasks(tasks.getTasks());
        ui.showUpdateTask(task);
        return ui.getOutput();
    }

    /**
     * Validates if the task list is not empty and the task index is valid.
     *
     * @param tasks The task list.
     * @throws ChatBuddyException If the task list is empty or the index is out of range.
     */
    private void validateTaskList(TaskList tasks) throws ChatBuddyException {
        if (tasks.size() == 0) {
            throw new ChatBuddyException("Task list is empty.");
        }
        if (taskIndex >= tasks.size() || taskIndex < 0) {
            throw new ChatBuddyException("Task number out of range.");
        }
    }

    /**
     * Updates the specified field of the task (description or date).
     *
     * @param task The task to update.
     * @throws ChatBuddyException If the field type is invalid.
     */
    private void updateTaskField(Task task) throws ChatBuddyException {
        switch (fieldType) {
        case DESCRIPTION_FIELD:
            task.updateDescription(newDetails);
            break;
        case DATE_FIELD:
            task.updateDate(newDetails);
            break;
        default:
            throw new ChatBuddyException("Invalid field type for update. Should be 'description' or 'date'");
        }
    }
}
