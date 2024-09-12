package duke.command;

import java.util.ArrayList;

import duke.Archive;
import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a restore archive command.
 */
public class RestoreCommand extends Command {
    /**
     * Constructor for a restore archive command.
     */
    public RestoreCommand() {

    }

    /**
     * Retrieves and clears the list of archived tasks, makes them active and writes the response message.
     *
     * @param tasks The list of tasks to add the archived tasks to.
     * @param ui The ui to write the response message.
     * @param storage The storage to add the archived tasks to.
     * @param archive The archive to retrieve the tasks from.
     * @throws BobException If an IO error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws BobException {
        ArrayList<Task> archivedTasks = archive.retrieve();
        tasks.add(archivedTasks);
        storage.add(archivedTasks);
        ui.restore(archivedTasks.size());
    }
}
