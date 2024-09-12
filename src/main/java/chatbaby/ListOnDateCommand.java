package chatbaby;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to list all tasks that are due on a specific date in the ChatBaby application.
 * Extends the Command class to implement the functionality for listing tasks on a specific date.
 */
public class ListOnDateCommand extends Command {
    private static final int BEGIN_INDEX = 13;

    /**
     * Constructs a ListOnDateCommand with the specified command body.
     *
     * @param commandBody The body of the command, which contains the date to filter tasks.
     */
    public ListOnDateCommand(String commandBody) {
        super(commandBody);
    }

    /**
     * Executes the list on date command, which displays all the tasks that are due on the specified date.
     *
     * @param tasks The list of tasks in the application.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving and loading tasks.
     * @throws ChatBabyException If there is an issue with the task list or date parsing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            // Parse the input date
            String time = commandBody.substring(BEGIN_INDEX).trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate givenDate = LocalDate.parse(time, formatter);
            boolean hasTask = tasks.listTasksOn(givenDate);
            if (!hasTask) {
                System.out.println("No tasks ending on this date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
}
