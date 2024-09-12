package duke.command;

import duke.Archive;
import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    /**
     * The task to add.
     */
    private Task task;

    /**
     * Constructor for an add command.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list of tasks and storage, and writes the response message.
     *
     * @param tasks The list of tasks to add the task to.
     * @param ui The ui to write the response message.
     * @param storage The storage to add the task to.
     * @param archive The archive for the command to interact with.
     * @throws BobException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws BobException {
        storage.add(this.task);
        tasks.add(this.task);
        ui.add(this.task, tasks.size());
    }
}
