package duke.command;

import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.io.IOException;

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
     * @throws BobException If an I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        try {
            storage.add(this.task);
            tasks.add(this.task);
            ui.add(this.task, tasks.size());
        } catch (IOException e) {
            throw new BobException("Sorry, something went wrong when updating your storage.");
        }
    }
}
