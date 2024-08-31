package lbot.command;

import java.time.LocalDateTime;

import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;
import lbot.task.Event;
import lbot.task.Task;


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
