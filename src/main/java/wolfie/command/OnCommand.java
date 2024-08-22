package wolfie.command;

import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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