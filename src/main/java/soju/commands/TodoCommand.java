package soju.commands;

import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;
import soju.tasks.Todo;

/**
 * TodoCommand handles commands starting with todo
 */
public class TodoCommand extends Command {
    private Todo todoTask;

    /**
     * Returns a TodoCommand
     * @param input command input as a String
     * @throws SojuException if invalid input
     */
    public TodoCommand(String input) throws SojuException {
        if (!input.startsWith("todo ")) {
            throw new SojuException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new SojuException("The description of a todo cannot be empty.");
        }
        // Create a new Tasks.Todo task with the extracted description
        todoTask = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        ui.printString("  " + tasks.addTask(todoTask));
        ui.printString("Now you have " + tasks.size() + " tasks in the list.");
    }
}
