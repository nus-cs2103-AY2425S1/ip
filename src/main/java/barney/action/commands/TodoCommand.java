package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.TodoTask;
import barney.ui.Ui;

/**
 * Represents a command to add a new todo task. Extends the Command class.
 */
public class TodoCommand extends Command {
    HashMap<String, String> argumentMap;

    /**
     * Represents a TodoCommand.
     *
     * This command is used to create a new todo item.
     *
     * @param argumentMap A HashMap containing the arguments for the command.
     */
    public TodoCommand(HashMap<String, String> argumentMap) {
        super("todo");
        this.argumentMap = argumentMap;
    }

    /**
     * Executes the TodoCommand, which adds a new TodoTask to the TaskList.
     *
     * @param tasks The TaskList to which the new TodoTask will be added.
     * @param ui    The Ui object used to print the added task.
     * @return true if the TodoCommand is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the argumentMap is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("description");

        TodoTask newTodo = new TodoTask(description);
        tasks.add(newTodo);
        ui.printAddedTask(newTodo, tasks.size());

        return true;
    }
}
