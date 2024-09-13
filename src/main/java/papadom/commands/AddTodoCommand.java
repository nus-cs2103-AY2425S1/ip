package papadom.commands;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.storage.Storage;
import papadom.storage.TaskList;
import papadom.tasks.Todo;
import papadom.utils.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String TEXT;
    /**
     * Constructs an AddTodoCommand with the specified text input.
     *
     * @param text The input string that contains the details of the todo task.
     */
    public AddTodoCommand(String text) {
        this.TEXT = text.trim();
    }

    /**
     * Executes the command to add a todo task to the task list.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface for outputting messages.
     * @param storage The storage system for saving the task list.
     * @throws IncorrectTaskInputFormatException If the input format is invalid or the description is missing.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        // Check if the text is only "todo" or "todo " without a meaningful task description
        boolean isTextOnlyTodo = this.TEXT.equals("todo") || this.TEXT.equals("todo ");
        if (isTextOnlyTodo) {
            throw new IncorrectTaskInputFormatException();
        }
        String taskDescription = this.TEXT.substring(5).trim();
        Todo todoTask = new Todo(taskDescription);
        return ui.output(taskList.addToList(todoTask));
    }
}
