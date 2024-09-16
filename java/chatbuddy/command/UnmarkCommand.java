package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to unmark tasks as done in the task list.
 */
public class UnmarkCommand extends Command {

    private final ArrayList<Integer> indices;

    /**
     * Constructs an UnmarkCommand with the specified indices of the tasks to be unmarked as done.
     *
     * @param indices The indices of the tasks to be unmarked as done.
     * @throws ChatBuddyException If the provided indices are not valid numbers.
     */
    public UnmarkCommand(String[] indices) throws ChatBuddyException {
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
     * Executes the unmark command, unmarking tasks as done in the task list and updating storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display the updated task status.
     * @param storage The storage to save the updated task list.
     * @throws ChatBuddyException If the task index is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        StringBuilder unmarkedTasks = new StringBuilder();
        for (int index : indices) {
            assert tasks.size() > 0 : "Task list should not be empty";
            if (index >= tasks.size() || index < 0) {
                throw new ChatBuddyException("Task number out of range.");
            }
            Task task = tasks.getTask(index);
            task.unmarkAsDone();
            unmarkedTasks.append(" ").append(task).append("\n");
        }

        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkTasks(unmarkedTasks.toString(), indices.size());
        return ui.getOutput();
    }
}
