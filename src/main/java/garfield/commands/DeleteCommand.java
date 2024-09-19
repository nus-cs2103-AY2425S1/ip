package garfield.commands;

import java.util.ArrayList;
import java.util.List;

import garfield.exceptions.GarfieldException;
import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;


/**
 * The DeleteCommand class represents a command to delete a task from the task list
 * in the Garfield chatbot application.
 */
public class DeleteCommand extends Command {

    private List<Integer> taskIds;

    /**
     * Constructs a new DeleteCommand with the specified task ID.
     *
     * @param taskId The ID of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskId) {
        this.taskIds = new ArrayList<>();
        this.taskIds.add(taskId);
    }

    /**
     * Constructs a new DeleteCommand with a list of specified task IDs
     *
     * @param taskIds The list of IDs of the tasks to be deleted from the task list.
     */
    public DeleteCommand(List<Integer> taskIds) {
        this.taskIds = new ArrayList<>();
        this.taskIds.addAll(taskIds);
        this.taskIds.sort((t1, t2) -> t2 - t1);
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the task list,
     * displaying a message to the user, and saving the updated task list to storage.
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GarfieldException {
        StringBuilder deletedTaskStrings = new StringBuilder();
        for (Integer taskId : this.taskIds) {
            deletedTaskStrings.append("\t").append(tasks.delete(taskId)).append("\n");
        }
        ui.showMessage("Alright you've got " + this.taskIds.size() + " less task.\n\n"
                + deletedTaskStrings + "\n\nEnjoy the extra 'fun' --or whatever you call it.");
        storage.save(tasks);

    }

    /**
     * Executes the DeleteCommand by removing the specified task from the task list,
     * and saving the updated task list to storage. Returns a String representing
     * the response from the chatbot.
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param storage The Storage object used to save the updated task list.
     * @return A String representing the message to the user.
     * @throws GarfieldException If an error occurs during command execution, such as an invalid task ID.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws GarfieldException {
        StringBuilder deletedTaskStrings = new StringBuilder();
        for (Integer taskId : this.taskIds) {
            deletedTaskStrings.append("\t").append(tasks.delete(taskId)).append("\n");
        }
        storage.save(tasks);
        return "Alright you've got " + this.taskIds.size() + " less task" + (this.taskIds.size() > 1 ? "s" : "")
                + ".\n\n" + deletedTaskStrings + "\n\nEnjoy the extra 'fun' --or whatever you call it.";
    }
}
