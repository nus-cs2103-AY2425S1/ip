package topaz.command;

import java.io.IOException;

import topaz.exception.InvalidCommandException;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (super.keyword.equals("mark")) {
                Task task = taskList.markAsDone(index);
                storage.save(taskList);
                return ui.showDoneTaskStatus(task);
            } else if (super.keyword.equals("unmark")) {
                Task task = taskList.markAsUndone(index);
                storage.save(taskList);
                return ui.showUndoneTaskStatus(task, taskList.getSize());
            } else {
                throw new InvalidCommandException(super.keyword);
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.showMarkIobError(index);
        } catch (IOException e) {
            return ui.showSaveIoeException(e);
        } catch (InvalidCommandException e) {
            return ui.showException(e);
        }
    }
}
