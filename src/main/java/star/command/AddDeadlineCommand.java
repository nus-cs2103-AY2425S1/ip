package star.command;

import java.time.LocalDateTime;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;
import star.task.Deadline;

/**
 * Represents a Command to add a new Deadline task to the task list.
 * Inherits from the Command class.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private String tag;
    private LocalDateTime date;

    /**
     * Constructs an addDeadlineCommand with the Deadline task description and deadline date.
     * @param description which is the task description.
     * @param by which is the deadline in String form for the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Constructs an addDeadlineCommand with the Deadline task description and deadline date.
     * @param description which is the task description.
     * @param date which is the deadline in LocalDate form for the task.
     */
    public AddDeadlineCommand(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Constructs an addDeadlineCommand with the Deadline task description, deadline date and the assigned tag.
     * @param description which is the task description.
     * @param by which is the deadline in String form for the task.
     * @param tag which is the tag assigned to this task.
     */
    public AddDeadlineCommand(String description, String by, String tag) {
        this.description = description;
        this.by = by;
        this.tag = tag;
    }

    /**
     * Constructs an addDeadlineCommand with the Deadline task description, deadline date and the assigned tag.
     * @param description which is the task description.
     * @param date which is the deadline in LocalDate form for the task.
     * @param tag which is the tag assigned to this task.
     */
    public AddDeadlineCommand(String description, LocalDateTime date, String tag) {
        this.description = description;
        this.date = date;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        Deadline deadline;
        if (tag != null) {
            if (date == null) {
                deadline = new Deadline(description, tag, by);
            } else {
                deadline = new Deadline(description, tag, date);
            }
        } else {
            if (date == null) {
                deadline = new Deadline(description, by);
            } else {
                deadline = new Deadline(description, date);
            }
        }
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        return ui.addSuccessMsg(deadline, tasks.length());
    }
}
