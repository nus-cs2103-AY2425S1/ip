package Commands;

import Tasks.Deadline;
import Tasks.Task;
import Tasks.TaskList;
import Main.*;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private String description;
    private String by;

    /**
     * Constructs a new AddDeadlineCommand.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date/time for the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component to save the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size());
    }
}
