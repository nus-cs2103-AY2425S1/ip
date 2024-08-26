package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ShowOnDateCommand implements Command {
    private LocalDate date;

    public ShowOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = tasks.getTasksOnDate(date); // Get the tasks on the specified date
        ui.showTasksOnDate(tasksOnDate, date); // Use the UI class to display tasks
    }

    @Override
    public boolean isExit() {
        return false;
    }
}