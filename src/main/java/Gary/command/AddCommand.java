package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * The {@code AddCommand} class represents a command to add a new task to the task list.
 * It extends the {@code Command} class and implements the behavior for adding a task.
 */
public class AddCommand extends Command {

    private Task task;  // The task to be added to the task list

    /**
     * Constructs an {@code AddCommand} object with the specified task to be added.
     *
     * @param task The {@code Task} object to add to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, which adds a task to the {@code TaskList},
     * updates the user through {@code Ui}, and saves the updated task list to storage.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.addTask(task, taskList.size());
        try {
            storage.saveTask(taskList);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        }
    }

    /**
     * Indicates that this command is not a "bye" command.
     *
     * @return {@code false} as this is not a "bye" command.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Checks if this {@code AddCommand} is equal to another object.
     * Two add commands are considered equal if they add the same task.
     *
     * @param obj The object to compare with this {@code AddCommand}.
     * @return {@code true} if the specified object is equal to this {@code AddCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            return this.task.equals(other.task);
        }
        return false;
    }
}
