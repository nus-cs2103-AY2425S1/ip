package justbot.command;

import java.time.LocalDateTime;

import justbot.storage.Storage;
import justbot.task.Deadline;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to add a Deadline task to the task list in the Justbot application.
 * The DeadlineCommand creates a new Deadline task with a specified description and due date/time.
 */
public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    /**
     * Constructs a DeadlineCommand with the specified task description and due date/time.
     *
     * @param description The description of the Deadline task.
     * @param by The date and time by which the task must be completed.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.deadlineTask = new Deadline(description, by);
    }

    /**
     * Executes the command to add the Deadline task to the task list.
     * The task is added to the task list, a confirmation message is displayed, and the task list is saved to storage.
     *
     * @param taskList The list of tasks to which the Deadline task is added.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.deadlineTask);
        taskList.sortTasksChronologically();
        storage.saveTasks(taskList);
        return ui.addTaskMessage(taskList, deadlineTask);
    }

    /**
     * Returns the Deadline task associated with this command.
     *
     * @return The Deadline task created by this command.
     */
    @Override
    public Task getTask() {
        return this.deadlineTask;
    }
}
