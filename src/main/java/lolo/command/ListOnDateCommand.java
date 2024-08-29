package lolo.command;

import lolo.Ui;
import lolo.storage.Storage;
import lolo.task.TaskList;

import java.time.LocalDateTime;

class ListOnDateCommand extends Command {
    private LocalDateTime date;

    public ListOnDateCommand(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksOnDate = tasks.getTasksOnDate(date);
        ui.showTasksOnDate(date.toLocalDate().toString(), tasksOnDate);
    }
}