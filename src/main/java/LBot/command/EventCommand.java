package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Event;
import LBot.task.Task;

import java.time.LocalDateTime;

/**
 * This class creates a new {@link Event}.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Public constructor for EventCommand.
     *
     * @param description   of the task.
     * @param startDateTime of the task.
     * @param endDateTime   of the task.
     */
    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Creates a new {@link Event}.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Task event = new Event(description, startDateTime, endDateTime);
        tasks.addTask(event);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(event);
    }

    @Override
    public String toString() {
        return "event command " + description + " " + startDateTime + " " + endDateTime;
    }

}
