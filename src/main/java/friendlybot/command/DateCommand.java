package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {
    private LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTasksOnDate(date);
        for (Task task : taskList) {
            Ui.print(task.toString());
        }
    }
}
