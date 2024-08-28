package hue.command;


import hue.HueException;
import hue.UI.UI;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;
import hue.task.Todo;

import java.io.IOException;
/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;
    /**
     * Creates an {@code AddTodoCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public AddTodoCommand(String fullCommand) throws HueException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HueException("The descriptipn of a todo cannot be empty.");
        }
        this.description = parts[1].trim();
    }
    @Override
    public void execute (TaskList tasks, UI ui, Storage storage) throws IOException {
        Task newTask = new Todo(description);

        tasks.add(newTask);

        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);

    }
}
