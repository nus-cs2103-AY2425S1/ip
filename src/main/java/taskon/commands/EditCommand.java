package taskon.commands;

import static taskon.common.Messages.MESSAGE_INVALID_EDIT;

import taskon.storage.Storage;
import taskon.task.Deadline;
import taskon.task.Event;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to edit an existing task in the task list.
 * The edit command allows modification of the description, deadline, or start/end times of tasks.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    private final int index;
    private final String fieldToEdit;
    private final String newValue;

    /**
     * Constructs an EditCommand to edit a task at the specified index with the provided field and new value.
     *
     * @param index       The index of the task to be edited.
     * @param fieldToEdit The field to edit ("description", "deadline", "start", "end").
     * @param newValue    The new value to update the field with.
     */
    public EditCommand(int index, String fieldToEdit, String newValue) {
        this.index = index;
        this.fieldToEdit = fieldToEdit;
        this.newValue = newValue;
    }
    /**
     * Executes the edit command.
     *
     * @param taskList The list of tasks managed by the application.
     * @param ui       The user interface that handles output and user interactions.
     * @param storage  The storage that handles data persistence.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            if (task instanceof Deadline && fieldToEdit.equals("deadline")) {
                ((Deadline) task).editTask("", newValue);
            } else if (task instanceof Event && fieldToEdit.equals("start")) {
                ((Event) task).editTask("", newValue, "");
            } else if (task instanceof Event && fieldToEdit.equals("end")) {
                ((Event) task).editTask("", "", newValue);
            } else if (fieldToEdit.equals("description")) {
                task.editDescription(newValue);
            } else {
                return ui.showError(MESSAGE_INVALID_EDIT);
            }
            storage.saveTasks(taskList);
            return ui.editTask(task);
        } catch (IndexOutOfBoundsException e) {
            return "You only have " + taskList.size() + " tasks!\n";
        }
    }
}
