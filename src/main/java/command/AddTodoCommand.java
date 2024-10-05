package command;

import exception.InvalidCommandKukiShinobuException;
import storage.Storage;
import task.TaskList;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to add a <code>Todo</code> <code>Task</code> to the <code>TaskList</code>.
 * It takes in a task description and optional tags as arguments and adds the task to the list.
 */
public class AddTodoCommand extends Command {
    private final Todo todo;

    /**
     * Creates an AddTodoCommand with the specified arguments.
     * If the argument is empty, it throws a InvalidCommandKukiShinobuException indicating the description is missing.
     *
     * @param arguments The string containing the task description and tags (e.g., "do homework #school #urgent").
     * @throws InvalidCommandKukiShinobuException if the task description is missing.
     */
    public AddTodoCommand(String arguments) throws InvalidCommandKukiShinobuException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandKukiShinobuException("Todo is missing description!");
        }

        // Split the arguments by space, identifying tags (starting with #)
        String[] parts = arguments.split(" ");
        StringBuilder descriptionBuilder = new StringBuilder();
        List<String> tags = new ArrayList<>();

        for (String part : parts) {
            if (part.startsWith("#")) {
                // Treat as a tag
                tags.add(part.substring(1)); // Remove the '#' from the tag
            } else {
                // Append to the description
                if (!descriptionBuilder.isEmpty()) {
                    descriptionBuilder.append(" ");
                }
                descriptionBuilder.append(part);
            }
        }

        // Ensure there is at least a description
        String description = descriptionBuilder.toString();
        if (description.isEmpty()) {
            throw new InvalidCommandKukiShinobuException("Todo is missing description!");
        }

        // Create a new Todo with the description and tags
        this.todo = new Todo(description, tags);
    }

    /**
     * Executes the command by adding the todo task to the task list.
     *
     * @param taskList The TaskList where the todo task will be added.
     * @param storage  The Storage instance to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.addTask(this.todo);
    }
}
