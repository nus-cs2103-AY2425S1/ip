package command;

import exceptions.BuddyException;
import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * UpdateCommand is responsible for updating specific fields of a task in the task list.
 * It can update the description, deadline, start date, or end date of the task, depending on the task type.
 */
public class UpdateCommand extends Command {

    /**
     * Enum representing different fields of a task that can be updated.
     */
    enum TaskField {
        DESCRIPTION, START, END, DEADLINE, UNKNOWN;

        /**
         * Converts a string to a corresponding TaskField enum.
         *
         * @param field the string representing the field to be updated.
         * @return the corresponding TaskField enum, or UNKNOWN if the string does not match any field.
         */
        public static TaskField stringToTaskField(String field) {
            if (field.equalsIgnoreCase("description")) {
                return TaskField.DESCRIPTION;
            } else if (field.equalsIgnoreCase("start")) {
                return TaskField.START;
            } else if (field.equalsIgnoreCase("end")) {
                return TaskField.END;
            } else if (field.equalsIgnoreCase("deadline")) {
                return TaskField.DEADLINE;
            } else {
                return TaskField.UNKNOWN;
            }
        }
    }

    private final int taskIndex;
    private final TaskField field;
    private final String updatedValue;

    /**
     * Constructs an UpdateCommand with the specified task index, field to update, and the updated value.
     *
     * @param taskIndex    the index of the task in the task list.
     * @param field        the field of the task to be updated.
     * @param updatedValue the new value for the specified field.
     */
    public UpdateCommand(int taskIndex, String field, String updatedValue) {
        this.taskIndex = taskIndex;
        this.field = TaskField.stringToTaskField(field);
        this.updatedValue = updatedValue;
    }

    /**
     * Executes the update command. It updates the specified field of the task at the given index in the task list.
     *
     * @param tasks   the list of tasks.
     * @param ui      the user interface instance to display messages.
     * @param storage the storage handler to save changes to the task list.
     * @return a success message after updating the task.
     * @throws BuddyException if the task index is invalid or if the task type does not support the specified update.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        // Check if the task index is valid
        if (taskIndex >= tasks.getTasks().size()) {
            return ui.displayUnavailableItem();
        }

        // Retrieve the task to be updated
        Task task = tasks.getTasks().get(taskIndex);

        // Update the specified field based on the task type
        switch (field) {
            case DESCRIPTION:
                task.updateDesc(updatedValue);
                break;
            case DEADLINE:
                // Ensure the task is of type Deadlines
                if (task instanceof Deadlines) {
                    ((Deadlines) task).updateDeadline(updatedValue);
                    break;
                } else {
                    throw new BuddyException("Are you sure that's for a deadline");
                }
            case START:
                // Ensure the task is of type Events
                if (task instanceof Events) {
                    ((Events) task).updateStartDate(updatedValue);
                    break;
                } else {
                    throw new BuddyException("Are you sure that's for an event");
                }
            case END:
                // Ensure the task is of type Events
                if (task instanceof Events) {
                    ((Events) task).updateEndDate(updatedValue);
                    break;
                } else {
                    throw new BuddyException("Are you sure that's for an event");
                }
            case UNKNOWN:
                throw new BuddyException("Which field would you like to update");
        }

        // Save the updated task list to storage
        storage.save(tasks.getTasks());

        // Display success message via UI
        return ui.displayUpdateSuccess();
    }
}