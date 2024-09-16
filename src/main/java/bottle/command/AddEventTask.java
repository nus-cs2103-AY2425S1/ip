package bottle.command;

import java.time.LocalDateTime;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Event;
import bottle.task.TaskList;

/**
 * The type Add event task.
 */
public class AddEventTask extends Command {
    /**
     * The Description.
     */
    private final String description;
    /**
     * The From.
     */
    private final LocalDateTime from;
    /**
     * The To.
     */
    private final LocalDateTime to;

    /**
     * Instantiates a new Add event task.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public AddEventTask(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";
        assert from != null : "Starting time cannot be empty!";
        assert to != null : "Ending time cannot be empty!";
        taskList.addTask(new Event(description, from, to));
        storage.saveTasks(taskList.getTaskList());
        return ui.printTaskAddedMessage(taskList.getTaskList());

    }
}
