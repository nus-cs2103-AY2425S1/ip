package friday.command;

import friday.task.Event;
import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified input.
     *
     * @param inputs The input array containing the description, start time, and end time of the event.
     * @throws FridayException If the input is invalid.
     */
    public EventCommand(String[] inputs) {
        if (inputs.length != 3) {
            throw new FridayException("\tInvalid input. 'event' command requires a description," +
                    " start, and end time.\n\tusage: event <string> /from <yyyy-MM-dd HHmm>" +
                    " /to <yyyy-MM-dd HHmm>.");
        }
        this.description = inputs[0];
        this.from = inputs[1];
        this.to = inputs[2];
    }

    /**
     * Executes the event command, adding the event task to the task list and saving it.
     *
     * @param tasks   The task list to be modified by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        try {
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }
}
