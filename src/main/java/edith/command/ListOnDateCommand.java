package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

import java.time.format.DateTimeParseException;

public class ListOnDateCommand extends Command {
    private String date;

    public ListOnDateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        try {
            tasks.listTasksOnDate(date, ui);
        } catch (DateTimeParseException e) {
            throw new EdithException(ui.invalidDateTimeError(), 1);
        }
    }
}
