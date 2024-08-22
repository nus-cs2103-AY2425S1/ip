import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OnCommand extends Command {
    private LocalDate date;

    public OnCommand(String arguments) {
        this.date = LocalDate.parse(arguments.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = tasks.getTasksOnDate(date);
        ui.showTasksOnDate(date, tasksOnDate);
    }
}