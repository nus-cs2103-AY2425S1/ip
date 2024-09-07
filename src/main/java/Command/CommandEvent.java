package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzException;

import task.Event;
import task.Task;

/**
 * Represents an "event" command in the Blitz application.
 */
public class CommandEvent extends Command {
    private String[] parameters;

    /**
     * Constructs a new CommandEvent object with specified command String and parameters as Array of String.
     *
     * @param values A variable number of String arguments associated with this Command object..
     */
    public CommandEvent(String... values) {
        super(values[0]);
        this.parameters = new String[values.length - 1];
        System.arraycopy(values, 1, parameters, 0, parameters.length);
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
        Task taskToAdd = new Event(parameters[0], "E", Task.convertStringToLocalDateTime(parameters[1]),
                Task.convertStringToLocalDateTime(parameters[2]), false);

        list.addTask(taskToAdd);
        storage.writeOneToFile(taskToAdd);

        return ui.getStringForTaskAdded(list.getSize(), taskToAdd);
    }
}
