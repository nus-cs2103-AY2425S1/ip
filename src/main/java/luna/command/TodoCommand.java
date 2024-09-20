package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Task;
import luna.task.Todo;

/**
 * Represents a command to add task without deadline to list of tasks.
 */
public class TodoCommand implements Command {
    private final Todo todo;
    private final Command previousCommand;

    /**
     * Creates a command to add a simple task with description.
     *
     *  @param inputs Inputs from user to create todo task.
     */
    public TodoCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1 || inputs[1].trim().isEmpty()) {
            throw new LunaException("Enter description for todo\n"
                    + "e.g. todo [description]");
        }

        this.todo = new Todo(inputs[1].trim());
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(todo, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskNumber = tasks.getTasks().indexOf(todo);
        Task deleted = tasks.deleteTask(taskNumber, storage);
        return ">>> undo 'todo' command\n"
                + "I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
