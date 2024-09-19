package bottle.command;

import java.time.LocalDateTime;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Deadline;
import bottle.task.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineTask extends Command {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The deadline time for the task.
     */
    private final LocalDateTime by;

    /**
     * Instantiates a new AddDeadlineTask.
     *
     * @param description the description of the task
     * @param by          the deadline time for the task
     */
    public AddDeadlineTask(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add the deadline task to the task list.
     *
     * @param taskList the task list to which the task will be added
     * @param ui       the user interface for displaying messages
     * @param storage   the storage for saving tasks
     * @return a message indicating the task has been added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";
        assert by != null : "Deadline time cannot be null!";

        taskList.addTask(new Deadline(description, by));
        storage.saveTasks(taskList.getTaskList());

        return ui.printTaskAddedMessage(taskList.getTaskList());
    }
}
