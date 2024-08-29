import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListOnDateCommand extends Command {
    public ListOnDateCommand(String commandBody) {
        super(commandBody);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            // Parse the input date
            String time = commandBody.substring(13);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate givenDate = LocalDate.parse(time, formatter);
            boolean hasTask = tasks.listTasksOn(givenDate);
            if (!hasTask) {
                System.out.println("No tasks ending on this date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }
}