package commands;

import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

/**
 * Command to add a Deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String description;
    private final String by; // Deadline date/time

    /**
     * Constructs a new AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param by          Deadline date/time for the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param tasks   TaskList to which the task is added.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message of task addition.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadlineTask = new Deadline(description, by);
        tasks.add(deadlineTask);
        storage.save(tasks);
        return ui.showTaskAdded(deadlineTask, tasks.size());
    }
}
