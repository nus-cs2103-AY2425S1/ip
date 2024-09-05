package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * Represents a command to edit the status of a task in the task list.
 * The task can either be marked as done or unmarked as not done.
 */
public class EditTaskCommand extends Command {
    protected boolean isDone;
    protected int index;

    /**
     * Constructs an EditTaskCommand to mark or unmark a task at a given index.
     *
     * @param isDone True if the task is to be marked as done, false if to be unmarked.
     * @param index  The index of the task to be edited in the task list.
     */
    public EditTaskCommand(Boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Executes the edit command. Marks the task as done or unmarks it as not done,
     * depending on the command. Saves the updated task list to storage.
     *
     * @param taskLists The task list where the task's status will be edited.
     * @param ui        The UI object to interact with the user and display messages.
     * @param storage   The storage object to save the updated task list.
     */
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        try {
            Task task = taskLists.getTask(index);
            if (isDone) {
                task.editStatus();
                ui.markTask(task);
            } else {
                ui.unmarkTask(task);
            }
            storage.saveTask(taskLists);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task list index is out of bounds!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list.");
        }
    }

    /**
     * Indicates that the EditTaskCommand does not terminate the application.
     *
     * @return false as the edit command does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
