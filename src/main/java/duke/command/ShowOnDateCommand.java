package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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