import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FilterCommand extends Command {

    String dateString;

    public FilterCommand(String dateString) {
        super(false);
        this.dateString = dateString;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidDateTimeException {
        try {
            LocalDate date = LocalDate.parse(this.dateString);
            ui.showList(tasks, date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date");
        }
    }

}
