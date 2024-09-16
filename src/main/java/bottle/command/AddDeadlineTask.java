package bottle.command;

import java.time.LocalDateTime;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Deadline;
import bottle.task.TaskList;

/**
 * The type Add deadline task.
 */
public class AddDeadlineTask extends Command {
    /**
     * The Description.
     */
    private final String description;
    /**
     * The By.
     */
    private final LocalDateTime by;

    /**
     * Instantiates a new Add deadline task.
     *
     * @param description the description
     * @param by          the by
     */
    public AddDeadlineTask(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";
        assert by != null : "Deadline time cannot be empty!";
        taskList.addTask(new Deadline(description, by));
        storage.saveTasks(taskList.getTaskList());
        return ui.printTaskAddedMessage(taskList.getTaskList());

    }
}
