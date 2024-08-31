package lbot.command;

import java.time.LocalDateTime;

import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;
import lbot.task.Deadline;
import lbot.task.Task;


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
