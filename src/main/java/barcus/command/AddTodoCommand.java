package barcus.command;

import barcus.storage.Storage;
import barcus.task.Task;
import barcus.task.Todo;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(this.description);
        tasks.addTask(t);
        ui.talk("Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.");
        output = "Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
