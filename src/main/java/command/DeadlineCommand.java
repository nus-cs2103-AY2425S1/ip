package command;

import java.time.LocalDateTime;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzDuplicateTaskException;
import exception.BlitzException;

import task.Deadline;
import task.Task;

/**
 * Represents a "deadline" command in the Blitz application.
 */
public class DeadlineCommand extends Command {
    private String[] parameters;

    /**
     * Constructs a new CommandDeadline object with specified command String and parameters as Array of String.
     *
     * @param values A variable number of String arguments associated with this Command object..
     */
    public DeadlineCommand(String... values) {
        super(values[0]);
        this.parameters = new String[values.length - 1];
        System.arraycopy(values, 1, parameters, 0, parameters.length);

        assert parameters.length == 2 : "CommandDeadline requires exactly two parameters";
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
        String description = parameters[0];
        LocalDateTime dateTime = Task.convertStringToLocalDateTime(parameters[1]);

        assert !description.isBlank() : "Deadline description must not be blank";

        Task taskToAdd = new Deadline(description, "D", dateTime, false);

        if (list.isTaskExist(taskToAdd)) {
            throw new BlitzDuplicateTaskException();
        }

        list.addTask(taskToAdd);
        storage.writeOneToFile(taskToAdd);

        return ui.getStringForTaskAdded(list.getSize(), taskToAdd);
    }
}
