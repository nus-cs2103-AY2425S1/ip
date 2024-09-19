package barcus.command;

import barcus.storage.Storage;
import barcus.task.Task;
import barcus.task.Todo;
import barcus.tasklist.TaskList;

/**
 * Command to add a new todo task
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description the description of the todo task
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the command by adding a todo task to the task list.
     *
     * @param tasks the task list to add the todo task to
     * @param storage the storage object to save the task
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = new Todo(this.description);
        tasks.addTask(t);
        output = "Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.";
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as adding a todo task does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
