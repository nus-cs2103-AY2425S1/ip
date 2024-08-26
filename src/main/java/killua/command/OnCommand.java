package killua.command;

import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class OnCommand extends Command {
    private LocalDate date;

    public OnCommand(String dateStr) throws KilluaException {
        this.date = parseDate(dateStr);
    }

    private LocalDate parseDate(String dateStr) throws KilluaException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new KilluaException("Please use the correct format for dates: yyyy-mm-dd");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDate(tasks, date);
    }
}
