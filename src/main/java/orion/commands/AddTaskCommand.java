package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;
import orion.tasks.Task;

/**
 * Represents a command to add a task to the task list.
 * <p>
 * This command adds a given {@link Task} to the {@link TaskList} and then
 * updates the user interface to reflect this addition.
 * </p>
 */
public class AddTaskCommand extends Command {

    private Task task;

    /**
     * Creates an {@code AddTaskCommand} with the specified task.
     *
     * @param task the {@link Task} to be added to the task list
     */
    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list and updating
     * the user interface.
     *
     * @param tasks  the {@link TaskList} to which the task will be added
     * @param storage the {@link Storage} for managing the task list
     * @param ui      the {@link Ui} for updating the user interface
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(task);
        ui.printAddTask(tasks, task);
    }
}
