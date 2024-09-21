package myapp.command;

import java.time.LocalDateTime;

import myapp.core.Storage;
import myapp.task.Deadline;
import myapp.task.Task;
import myapp.task.TaskList;



/**
 * Represents a command that adds a new {@link Deadline} task to the task list.
 * This command stores the description and the deadline date/time, and it adds the task to the list
 * when executed.
 */
public class DeadlineCommand extends AddCommand {
    /** The date and time by which the task should be completed. */
    private LocalDateTime dateTime;

    /**
     * Constructs a DeadlineCommand with the specified task description and deadline.
     *
     * @param s  The description of the deadline task.
     * @param dt The date and time by which the task should be completed.
     */
    public DeadlineCommand(String s, LocalDateTime dt) {
        super(s);
        this.dateTime = dt;
    }

    /**
     * Executes the command by adding a new {@link Deadline} task to the task list.
     * The task is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list to which the deadline task will be added.
     * @param storage The storage system used to save the task list.
     * @return A string message confirming the addition of the task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = new Deadline(description, dateTime);
        tasks.add(task);
        saveTasks(tasks, storage);
        return printAddMessage(tasks, task);
    }
}
