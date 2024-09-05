package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * The {@code EditTaskCommand} class represents a command to mark or unmark a task as done.
 * It extends the {@code Command} class and implements the behavior for editing a task's status.
 */
public class EditTaskCommand extends Command {

    private boolean isDone;  // Flag indicating whether to mark or unmark the task as done
    private int index;  // The index of the task to be edited

    /**
     * Constructs an {@code EditTaskCommand} object with the specified status and task index.
     *
     * @param isDone The flag indicating whether to mark or unmark the task as done.
     * @param index The index of the task to edit.
     */
    public EditTaskCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Executes the edit task command, which marks or unmarks a task as done in the {@code TaskList},
     * updates the user through {@code Ui}, and saves the updated task list to storage.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            if (isDone) {
                ui.markTask(task);
                task.editStatus();
            } else {
                ui.unmarkTask(task);
            }
            storage.saveTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task list index is out of bounds!");
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
}
