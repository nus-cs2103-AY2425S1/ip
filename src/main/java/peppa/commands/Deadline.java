package peppa.commands;

import java.time.LocalDateTime;

import peppa.data.TaskList;
import peppa.storage.Storage;
import peppa.tasks.DeadlineTask;
import peppa.tasks.Task;
import peppa.ui.Ui;

/**
 * Class representing the deadline command.
 */
public class Deadline extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new deadline command.
     *
     * @param description the description of the deadline.
     * @param by the deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        Task t = new DeadlineTask(description, by);
        list.add(t);
        return addTask(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
