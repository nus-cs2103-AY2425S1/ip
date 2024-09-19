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
     * Constructs a {@code DeleteCommand} with the specified index of the task to be deleted.
     *
     * @param num The index of the task to be deleted (0-based index).
     */
    public ArchiveCommand(int num) {
        // Assert that the index passed in is non-negative
        assert num >= 0 : "Task index must be non-negative";
        this.num = num;
    }

    /**
     * Executes the delete command by removing the specified task from the task list, saving the updated list to storage,
     * and returning a confirmation message to the user.
     *
     * @param tasks   The {@link TaskList} from which the task will be deleted.
     * @param ui      The {@link Ui} instance used to display messages and interact with the user.
     * @param storage The {@link Storage} instance responsible for persisting the updated task list.
     * @param archiveTasks   The {@link TaskList}, not used in this command.
     * @param archiveStorage The {@link Storage} instance, not used in this command.
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
//        if (this.num > tasks.size() - 1) {
//            throw new SnipeException("This list item does not exist!\n"
//                    + tasks.listLength());
//        } else {
//            Task toRemove = tasks.getTask(this.num);
//
//            // Assert that the task exists and is not null (task retrieval is successful)
//            assert toRemove != null : "Task should not be null";
//
//            tasks.deleteTask(this.num);
//            storage.saveTaskList(tasks);
//            String message = "Noted. I've removed this task:\n"
//                    + toRemove.toString()
//                    + tasks.listLength();
//            return message;
//        }
        return "";
    }
}
