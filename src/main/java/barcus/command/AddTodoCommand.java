package barcus.command;

import barcus.storage.Storage;
import barcus.task.Task;
import barcus.task.Todo;
import barcus.tasklist.TaskList;

/**
 * Command to add in a todo task
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Constructor
     * @param description String
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = new Todo(this.description);
        tasks.addTask(t);
        output = "Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
