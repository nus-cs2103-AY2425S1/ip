package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.task.Event;
import vinegar.task.Task;
import vinegar.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the addition of a new event task.
 * <p>
 * This command allows the user to specify the description of an event task along with its start time (/from)
 * and end time (/to) using the format yyyy-MM-dd HH:mm. The event is then added to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an AddEventCommand with the user input.
     *
     * @param inputParts The user's input split into parts containing the description, from time, and to time.
     * @throws VinegarException If the input is invalid or dates are not in the correct format.
     */
    public AddEventCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify the description and event time.");
        String[] eventParts = inputParts[1].split(" /from | /to ");
        Validator.validateParts(eventParts, 3, "Please specify the event time using /from and /to in the format yyyy-MM-dd HH:mm.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Parse and validate 'from' date
        try {
            this.from = LocalDateTime.parse(eventParts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'from' date format. Please use yyyy-MM-dd HH:mm format.");
        }

        // Parse and validate 'to' date
        try {
            this.to = LocalDateTime.parse(eventParts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'to' date format. Please use yyyy-MM-dd HH:mm format.");
        }

        this.description = eventParts[0];
    }

    /**
     * Executes the AddEventCommand, adding the event to the task list.
     *
     * @param tasks    The task list where the event will be added.
     * @param ui       The UI component to display feedback to the user.
     * @param storage  The storage component to save the updated task list.
     * @throws VinegarException If there is an issue with task execution.
     * @throws IOException      If there is an I/O error during file operations.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task eventTask = new Event(description, from, to);
        tasks.addTask(eventTask);
        storage.save(tasks.getTasks());
        return ui.printTaskAdded(eventTask, tasks.size());
    }
}
