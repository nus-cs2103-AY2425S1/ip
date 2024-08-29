package topaz.command;

import java.io.IOException;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.task.Task;
import topaz.ui.Ui;

/**
 * Represents a command to mark a task as done or undone in the task management system.
 * The task to be marked is identified by its index in the TaskList.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand with the specified keyword and index.
     *
     * @param keyword The keyword specifying the action (e.g., "mark" or "unmark").
     * @param index   The index of the task to be marked (1-based index).
     */
    public MarkCommand(String keyword, int index) {
        super(keyword);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (super.keyword.equals("mark")) {
                Task task = taskList.markAsDone(index);
                ui.showDoneTaskStatus(task);
            } else {
                Task task = taskList.markAsUndone(index);
                ui.showUndoneTaskStatus(task, taskList.getSize());
            }
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showMarkIobError(index);
        } catch (IOException e) {
            ui.showSaveIoeException(e);
        }
    }
}
