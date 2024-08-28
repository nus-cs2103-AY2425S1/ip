import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    private TaskList taskList;
    private String date;
    public DateCommand(TaskList taskList, String date) {
        this.taskList = taskList;
        this.date = date;
    }

    @Override
    public void execute() throws StobberiException {
        try {
            taskList.filterListByDate(date);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException("Date needs to be in the format dd-MM-yyyy\n Example: 27-12-2004\n" + e.getMessage());
        }
    }
}