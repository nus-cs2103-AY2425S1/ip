package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Deadline;
import LBot.task.Event;
import LBot.task.Task;

import java.time.LocalDateTime;

/**
 * This class creates a new {@link Deadline}.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dueDate;

    /**
     * Public constructor for DeadlineCommand.
     *
     * @param description of the task.
     * @param dueDate     of the task.
     */
    public DeadlineCommand(String description, LocalDateTime dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Creates a new {@link Deadline}.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Task deadline = new Deadline(description, dueDate);
        tasks.addTask(deadline);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(deadline);
    }

    @Override
    public String toString() {
        return "deadline command " + description + " " + dueDate;
    }
}
