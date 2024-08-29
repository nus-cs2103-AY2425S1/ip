package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

/**
 * The {@code DeadlineCommand} class represents a command that, when executed, adds a new
 * deadline task to the task list. The command stores the task's description and its due date and time.
 *
 */
public class DeadlineCommand implements Command {

    private final String taskDescription;
    private final LocalDateTime time;

    /**
     * Constructs a new {@code DeadlineCommand} with the specified task description and due date.
     * @param taskDescription The description of the task to be added.
     * @param time The due date and time of the deadline task.
     */
    public DeadlineCommand(String taskDescription, LocalDateTime time) {
        this.taskDescription = taskDescription;
        this.time = time;

    }

    /**
     * Executes the Deadline command, adding a new deadline task to the task list,
     * saving it to storage, and displaying the added task via the user interface.
     *
     * @param storage The storage handler used for saving the new deadline task.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command.
     */
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        Deadline d = tasks.addDeadline(this.taskDescription, this.time);
        storage.writeDeadlineToFile(d);
        ui.displayTaskAdded(d, tasks.getSize());
    }

    /**
     * Indicates that this command does not signal the application to exit.
     *
     * @return {@code false}, indicating that this command does not cause the
     *         application to terminate.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns the due date and time of the deadline task.
     *
     * @return The due date and time of the deadline task.
     */
    public LocalDateTime getDueDate() {
        return this.time;
    }
}
