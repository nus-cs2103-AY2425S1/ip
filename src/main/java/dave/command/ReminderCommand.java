package dave.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import dave.exceptions.InvalidDescriptionException;
import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents a command that reminds the user of undone tasks in the task list.
 * It can list either all undone tasks or only the overdue tasks based on the user's input.
 */
public class ReminderCommand extends Command {
    private String reminderTaskType;

    /**
     * Enum to represent valid reminder types, either 'overdue' for overdue tasks
     * or 'all' for all undone tasks.
     */
    public enum ReminderType {
        overdue, all
    }

    /**
     * Constructs a ReminderCommand with the specified reminder type.
     * Validates the reminder type to ensure it's either 'overdue' or 'all'.
     *
     * @param reminderTaskType The type of reminder: either 'overdue' or 'all'.
     * @throws InvalidDescriptionException if the reminderTaskType is invalid.
     */
    public ReminderCommand(String reminderTaskType) throws InvalidDescriptionException {
        // Validate reminderTaskType using the enum
        try {
            ReminderType.valueOf(reminderTaskType);
            this.reminderTaskType = reminderTaskType;
        } catch (IllegalArgumentException e) {
            throw new InvalidDescriptionException("Invalid reminder type provided. Use 'overdue' or 'all' as valid types.");
        }
    }

    /**
     * Executes the reminder command, returning a list of undone tasks based on the reminder type.
     * If the reminder type is 'overdue', only overdue undone tasks will be displayed.
     * If the reminder type is 'all', all undone tasks will be displayed.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be displayed.
     * @param storage The {@code Storage} object to handle saving the task list (not used in this command).
     * @param ui      The {@code Ui} object to handle user interaction (not used in this command).
     * @return A string representation of all undone or overdue tasks in the task list.
     * @throws IllegalStateException if an unexpected reminder task type is encountered (should not occur due to validation in the constructor).
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "Task list should not be null.";
        assert tasks.getSize() >= 0 : "Task list size should be non-negative.";

        String str;
        ArrayList<Task> undoneTasks;

        // Choose the appropriate task list based on the reminderTaskType
        if ("overdue".equals(reminderTaskType)) {
            undoneTasks = tasks.findUndoneDeadlineTasks();
            str = "Can you get to work? You still have the following tasks that haven't been completed, and they're overdue:\n";
        } else if ("all".equals(reminderTaskType)) {
            undoneTasks = tasks.findUndoneTasks();
            str = "Hmmmm. Time is ticking. You still have the following tasks that haven't been completed:\n";
        } else {
            throw new IllegalStateException("Unexpected reminder task type: " + reminderTaskType);
        }
        if (undoneTasks.isEmpty()) {
            return "I can't believe you didn't have any overdue undone tasks!";
        }
        return str + String.join("\n", undoneTasks.stream()
                .map(Task::toString)
                .collect(Collectors.toList()));
    }
}
