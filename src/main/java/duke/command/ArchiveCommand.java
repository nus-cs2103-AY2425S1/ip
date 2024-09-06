package duke.command;

import java.util.ArrayList;

import duke.Archive;
import duke.BobException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents an archive command.
 */
public class ArchiveCommand extends Command {
    /**
     * Constructor for a new archive command.
     */
    public ArchiveCommand() {}

    /**
     * Archives the active tasks, clears the list and storage, and writes the response message.
     *
     * @param tasks The list of tasks to archive and clear.
     * @param ui The ui to write the response message.
     * @param storage The storage to clear.
     * @param archive The archive to store the tasks.
     * @throws BobException If an IO error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws BobException {
        ArrayList<Task> activeTasks = tasks.all();

        archive.archive(activeTasks);
        tasks.clear();
        storage.clear();
        ui.archive(activeTasks.size());
    }
}
