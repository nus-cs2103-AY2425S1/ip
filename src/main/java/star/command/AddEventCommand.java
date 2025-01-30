package star.command;

import java.time.LocalDateTime;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;
import star.task.Event;

/**
 * Represents a Command to add a new Event task to the task list.
 * Inherits from the Command class.
 */
public class AddEventCommand extends Command {
    private String description;
    private String by;
    private String tag;
    private LocalDateTime date1;
    private LocalDateTime date2;

    /**
     * Constructs an addEventCommand with the Event task description and event date and time.
     * @param description which is the task description.
     * @param by which is the event date and time in String form.
     */
    public AddEventCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Constructs an addEventCommand with the Event task description and event date and time.
     * @param description which is the task description.
     * @param date1 which is the start date and time of the event.
     * @param date2 which is the end date and time of the event.
     */
    public AddEventCommand(String description, LocalDateTime date1, LocalDateTime date2) {
        this.description = description;
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * Constructs an addEventCommand with the Event task description, event date and time and the assigned tag.
     * @param description which is the task description.
     * @param by which is the event date and time in String form.
     * @param tag which is the tag assigned to this task.
     */
    public AddEventCommand(String description, String by, String tag) {
        this.description = description;
        this.by = by;
        this.tag = tag;
    }

    /**
     * Constructs an addEventCommand with the Event task description, event date and time and the assigned tag.
     * @param description which is the task description.
     * @param date1 which is the start date and time of the event.
     * @param date2 which is the end date and time of the event.
     * @param tag which is the tag assigned to this task.
     */
    public AddEventCommand(String description, LocalDateTime date1, LocalDateTime date2, String tag) {
        this.description = description;
        this.date1 = date1;
        this.date2 = date2;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        Event event;
        if (tag == null) {
            if (date1 == null || date2 == null) {
                event = new Event(description, by);
            } else {
                event = new Event(description, date1, date2);
            }
        } else {
            if (date1 == null || date2 == null) {
                event = new Event(description, tag, by);
            } else {
                event = new Event(description, tag, date1, date2);
            }
        }

        tasks.addTask(event);
        storage.save(tasks.getTasks());
        return ui.addSuccessMsg(event, tasks.length());
    }
}
