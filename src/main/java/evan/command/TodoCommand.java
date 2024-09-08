package evan.command;

import evan.service.TaskList;
import evan.task.Todo;

/**
 * Represents a command that the chatbot can execute to create a Todo.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Instantiates a Todo object.
     *
     * @param description Description of the Todo to be created.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        return "Got it. I've added this todo:\n" + todo;
    }
}
