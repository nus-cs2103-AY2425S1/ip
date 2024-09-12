package commands;

import java.io.IOException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Todo;

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
        this.description = extractDescription(input);
    }

    /**
     * Extracts the description part from the input string.
     *
     * @param input The input string containing the todo description.
     * @return The extracted description.
     * @throws SkibidiException If the description is empty.
     */
    private String extractDescription(String input) throws SkibidiException {
        if (input.length() < 5) {
            throw new SkibidiException("OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new SkibidiException("OOPS!!! The description of a todo cannot be empty.");
        }
        return description;
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        try {
            Todo todo = new Todo(description, false);
            storage.addTask(todo);
            return ui.outputMessage("Got it. I've added this task:\n  " + todo);
        } catch (SkibidiException | IOException e) {
            return ui.outputMessage(e.getMessage());
        }
    }
}
