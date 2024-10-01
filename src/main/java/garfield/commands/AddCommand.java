package garfield.commands;

import garfield.storage.Storage;
import garfield.tasks.Task;
import garfield.tasks.TaskList;
import garfield.ui.Ui;


/**
 * The AddCommand class represents a command to add a new task to the task list
 * in the Garfield chatbot application.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs a new AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand by adding the task to the task list, displaying a
     * message to the user, and saving the updated task list to storage.
     *
     * @param tasks The TaskList to which the task will be added.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showMessage("Fine. I'll add '" + task.getTaskDescription() + "' to the list.\n\n\t"
                + this.task + "\n\nJust what you needed to boost your list to a grand total of "
                + tasks.size() + " task" + ((tasks.size() == 1) ? "" : "s") + ". Lucky you.");
        storage.save(tasks);
    }

    /**
     * Executes the AddCommand by adding the task to the task list
     * and saving the updated task list to storage. Returns a String representing
     * the response by the chatbot.
     *
     * @param tasks The TaskList to which the task will be added.
     * @param storage The Storage object used to save the updated task list.
     * @return A String representing the message to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(this.task);
        storage.save(tasks);
        return "Fine. I'll add '" + task.getTaskDescription() + "' to the list.\n\n\t"
                + this.task + "\n\nJust what you needed to boost your list to a grand total of "
                + tasks.size() + " task" + ((tasks.size() == 1) ? "" : "s") + ". Lucky you.";
    }
}
