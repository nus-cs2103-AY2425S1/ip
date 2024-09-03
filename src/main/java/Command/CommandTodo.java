package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;
import task.Task;
import task.Todo;

/**
 * Represents a "todo" command in the Blitz application.
 */
public class CommandTodo extends Command {
    private String param;

    /**
     * Constructs a new CommandTodo object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param param String containing the parameter for this command.
     */
    public CommandTodo(String command, String param) {
        super(command);
        this.param = param;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to add the new Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file to add the new Task.
     * @return Execution result of the command as String.
     * @throws BlitzException If I/O error occurs.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task task = new Todo(this.param, "T", false);

        list.addTask(task);
        storage.writeOneToFile(task);
        return ui.printTaskAdded("T", list.getSize(), task);
    }
}
