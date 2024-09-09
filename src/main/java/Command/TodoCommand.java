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
public class TodoCommand extends Command {
    private String parameter;

    /**
     * Constructs a new CommandTodo object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param parameter String containing the parameter for this command.
     */
    public TodoCommand(String command, String parameter) {
        super(command);
        this.parameter = parameter;
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
        Task taskToAdd = new Todo(this.parameter, "T", false);

        list.addTask(taskToAdd);
        storage.writeOneToFile(taskToAdd);

        return ui.getStringForTaskAdded(list.getSize(), taskToAdd);
    }
}
