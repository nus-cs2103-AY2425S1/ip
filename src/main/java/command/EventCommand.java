package command;

import java.time.LocalDateTime;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzDuplicateTaskException;
import exception.BlitzException;

import task.Event;
import task.Task;

/**
 * Represents an "event" command in the Blitz application.
 */
public class EventCommand extends Command {
    private String[] parameters;

    /**
     * Constructs a new CommandEvent object with specified command String and parameters as Array of String.
     *
     * @param values A variable number of String arguments associated with this Command object.
     */
    public EventCommand(String... values) {
        super(values[0]);
        this.parameters = new String[values.length - 1];
        System.arraycopy(values, 1, parameters, 0, parameters.length);

        assert parameters.length == 3 : "Event command must have exactly three parameters";
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
        LocalDateTime startDateTime = Task.convertStringToLocalDateTime(parameters[1]);
        LocalDateTime endDateTime = Task.convertStringToLocalDateTime(parameters[2]);

        assert !description.isBlank() : "Event description must not be blank";

        Task taskToAdd = new Event(description, "E", startDateTime, endDateTime, false);

        if (list.isTaskExist(taskToAdd)) {
            throw new BlitzDuplicateTaskException();
        }

        list.addTask(taskToAdd);
        storage.writeOneToFile(taskToAdd);

        return ui.getStringForTaskAdded(list.getSize(), taskToAdd);
    }
}
