package command;

import java.io.IOException;
import java.time.LocalDate;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ShowOnDateCommand implements Command {
    private LocalDate date;

    public ShowOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasksOnDate(date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}