package assistinator.commands;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;
import assistinator.tasks.TodoTask;

/**
 * Represents todo command.
 */
public class TodoCommand extends Command {
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        TodoTask newTodo = parseTodo(input);
        tasks.addTask(newTodo);
        return String.format(
                "I have added this task:\n%s\nYour evil agenda contains %d%s",
                newTodo,
                tasks.size(),
                tasks.size() == 1 ? " task" : " tasks"
        );
    }

    private TodoTask parseTodo(String input) throws AssistinatorException {
        String description = input.substring(input.indexOf(' ') + 1).trim();
        if (description.isEmpty() || description.equalsIgnoreCase("todo")) {
            throw new AssistinatorException("Please follow format: todo {task description}");
        }
        return new TodoTask(description);
    }
}
