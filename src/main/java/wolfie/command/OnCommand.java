package wolfie.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to list all tasks on a specific date.
 */
public class OnCommand extends Command {
    private final LocalDate date;

    public OnCommand(String arguments) {
        this.date = LocalDate.parse(arguments.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = tasks.getTasksOnDate(date);
        ui.showTasksOnDate(date, tasksOnDate);
    }
}
