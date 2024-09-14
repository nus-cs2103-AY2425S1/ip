package jeff.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;

/**
 * Represents a "Check what task is on a certain date" command.
 */
public class DateCommand extends Command {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! It should be \"date(or dt) yyyy-mm-dd\"!";

    /**
     * Constructor for DateCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public DateCommand(String input) {
        super(input);
    }

    /**
     * Checks if the format of the user input is wrong or not.
     *
     * @return true if the user input is in the wrong format and false otherwise.
     */
    private boolean isFormatWrong() {
        return !this.getInput().matches("date .+") && !this.getInput().matches("dt .+");
    }

    /**
     * Returns the LocalDate object representation of the date in the user input.
     *
     * @return LocalDate of the date string.
     * @throws JeffException if the format of the user input is wrong.
     */
    private LocalDate getDate() throws JeffException {
        assert !this.isFormatWrong() : "Command should not be in the wrong format when getting date";

        String[] taskParts = this.getInput().split(" ", 2);
        String taskPeriod = taskParts.length > 1 ? taskParts[1] : "";

        try {
            return LocalDate.parse(taskPeriod);
        } catch (DateTimeParseException e) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }
    }

    /**
     * Returns a TaskList filtered by the given date.
     *
     * @param tasks Task list.
     * @param taskDate Given date.
     * @return Filtered task list.
     * @throws JeffException if there is no task on the given date.
     */
    private TaskList filterByDate(TaskList tasks, LocalDate taskDate) throws JeffException {
        TaskList filteredTasks = tasks.filterByDate(taskDate);
        assert filteredTasks != null : "Filtered tasks list cannot be null";

        if (filteredTasks.isEmpty()) {
            throw new JeffException("No deadlines/events on " + Parser.toDateString(taskDate) + "!");
        }

        return filteredTasks;
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when the user searches for tasks on a
     * specific date by filtering the task list based on the date input.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        assert tasks != null : "Task list should not be null";

        if (this.isFormatWrong()) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }

        LocalDate taskDate = this.getDate();
        assert taskDate != null : "Task date cannot be null";

        TaskList filteredTasks = this.filterByDate(tasks, taskDate);

        String taskListString = filteredTasks.toString();

        return Parser.addSpaceInFrontOfEachLine(
                String.format(
                        "Here are the tasks for %s:\n%s",
                        Parser.toDateString(taskDate),
                        taskListString
                )
        );
    }
}
