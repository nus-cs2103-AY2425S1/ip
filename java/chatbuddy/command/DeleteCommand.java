package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final ArrayList<Integer> indices;

    /**
     * Constructs a DeleteCommand with the specified indices of the tasks to be deleted.
     *
     * @param indices The indices of the tasks to be deleted.
     * @throws ChatBuddyException If the provided indices are not valid numbers.
     */
    public DeleteCommand(String[] indices) throws ChatBuddyException {
        this.indices = new ArrayList<>();
        try {
            for (String index : indices) {
                int parsedIndex = Integer.parseInt(index) - 1;
                assert parsedIndex >= 0 : "Index cannot be negative";
                this.indices.add(parsedIndex);
            }
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Invalid task number.");
        }
    }

    /**
     * Executes the delete command, removing tasks from the task list and updating storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws ChatBuddyException If the task index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Collections.sort(indices, Collections.reverseOrder());

        StringBuilder deletedTasks = new StringBuilder();
        for (int index : indices) {
            assert tasks.size() > 0 : "Task list should not be empty";
            if (index >= tasks.size() || index < 0) {
                throw new ChatBuddyException("Task number out of range.");
            }
            Task removedTask = tasks.getTask(index);
            tasks.removeTask(index);
            deletedTasks.append(" ").append(removedTask).append("\n");
        }

        storage.saveTasks(tasks.getTasks());
        ui.showDeleteTasks(deletedTasks.toString(), tasks.size(), indices.size());
        return ui.getOutput();
    }
}
