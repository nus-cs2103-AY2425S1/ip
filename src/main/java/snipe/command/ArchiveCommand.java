package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

public class ArchiveCommand extends Command{
    private int num;

    /**
     * Constructs a {@code ArchiveCommand} with the specified index of the task to be archived.
     *
     * @param num The index of the task to be archived (0-based index).
     */
    public ArchiveCommand(int num) {
        // Assert that the index passed in is non-negative
        assert num >= 0 : "Task index must be non-negative";
        this.num = num;
    }

    /**
     * Executes the archive command by removing the specified task from the task list, saving the updated list to storage,
     * and returning a confirmation message to the user.
     *
     * @param tasks          The {@link TaskList} from which the task will be archived.
     * @param ui             The {@link Ui} instance used to display messages and interact with the user.
     * @param storage        The {@link Storage} instance responsible for persisting the updated task list.
     * @param archiveTasks   The {@link TaskList} from which the archived task will be added to.
     * @param archiveStorage The {@link Storage} instance responsible for persisting the updated archived task list.
     * @return A confirmation message indicating that the task has been successfully removed, along with the updated task count.
     * @throws SnipeException If the specified task index is out of range or if an error occurs while saving the task list.
     * @throws IOException    If an I/O error occurs during the saving process.
     */
    @Override
    public String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task toArchive = tasks.getTask(this.num);

            // Assert that the task exists and is not null (task retrieval is successful)
            assert toArchive != null : "Task should not be null";

            tasks.archiveTask(this.num, archiveTasks);
            storage.saveTaskList(tasks);
            archiveStorage.saveTaskList(archiveTasks);
            String message = "Noted. I've archived this task:\n"
                    + toArchive.toString()
                    + tasks.listLength();
            return message;
        }
    }
}
