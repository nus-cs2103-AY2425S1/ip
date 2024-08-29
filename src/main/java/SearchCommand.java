import java.time.LocalDate;

public class SearchCommand extends Command{
    private LocalDate searchDate;

    public  SearchCommand(LocalDate searchDate) {
        this.searchDate = searchDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSearchList(tasks.getTasks(), searchDate);
    }
}
