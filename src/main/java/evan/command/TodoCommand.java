package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.showSuccess("Got it. I've added this todo:\n" + todo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
