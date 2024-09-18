package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Task;
import skibidi.task.Todo;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;
    /**
     * Creates a todo command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public TodoCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 2) {
            throw new SkibidiException("Error: todo description must not be empty. "
                    + "\nTo add a todo task, use the format: todo <description>");
        } else {
            this.description = everythingAfterCommand(input);
        }
    }

    private String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Todo(description);
        taskList.addTask(task);
        storage.save(taskList);
        return "Got it. I added this to the goon quest: \n" + task
                + "\nNow you have " + taskList.size() + " quests in the list.";
    }
}
