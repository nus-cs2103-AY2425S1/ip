package gavinchatbot.command;

import java.io.IOException;
import java.time.LocalDate;

import gavinchatbot.task.Deadline;
import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The date by which the task must be completed.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks The task list to add the deadline to.
     * @param ui The UI that will show the output to the user.
     * @param storage The storage where the task list is saved.
     * @return A message indicating the deadline has been successfully added.
     * @throws GavinException If there is an error during the execution.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        Task task = new Deadline(description, by.toString());
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return false as adding a deadline does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
