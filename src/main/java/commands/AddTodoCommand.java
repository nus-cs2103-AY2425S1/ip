package commands;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Todo;

import java.io.IOException;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddTodoCommand.
     *
     * @param input The input string containing the todo description.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public AddTodoCommand(String input) throws SkibidiException {
        if (input.length() < 5) {
            throw new SkibidiException("OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new SkibidiException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            Todo todo = new Todo(description, false);
            storage.addTask(todo);
            ui.printMessage("Got it. I've added this task:\n  " + todo);
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}
