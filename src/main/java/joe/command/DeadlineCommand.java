package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Deadline;
import joe.task.Task;
import joe.task.TaskList;

import java.time.LocalDate;

/**
 * This class represents the 'deadline' command.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        Task newTask = new Deadline(this.description, this.by);
        taskList.addTask(newTask);
        ui.printAddedTask(newTask, taskList.getSize());
    }
}
