package hana.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hana.HanaException;
import hana.storage.Storage;
import hana.task.Event;
import hana.task.Task;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to add Event task.
 * When executed, this command will add new Event task, print a message and save.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new EventCommand with a description, start and end date.
     *
     * @param input The input from user.
     * @throws HanaException If an error occurs.
     */
    public EventCommand(String input) throws HanaException {
        try {
            String[] parts = input.substring(5).split(" /from | /to ");
            if (parts.length < 3) {
                throw new HanaException("Event task must have a description, start time, and end time.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.from = LocalDateTime.parse(parts[1].trim(), formatter);
            this.to = LocalDateTime.parse(parts[2].trim(), formatter);
            this.description = parts[0].trim();
        } catch (DateTimeParseException e) {
            throw new HanaException("Please provide a valid deadline command in the format: "
                    + "event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]");
        }
    }

    /**
     * Executes the command to add Event task.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object to handle reading/writing of tasks.
     * @throws HanaException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
        Task task = new Event(description, from, to);
        taskList.addTask(task);
        ui.printAdd(task, taskList.getTasks().size());
        storage.save();
    }
}
