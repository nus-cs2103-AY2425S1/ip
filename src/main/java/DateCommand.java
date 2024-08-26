import java.time.LocalDate;

public class DateCommand extends Command {
    private LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTasksOnDate(date);
    }
}
