package stobberi.command;

import stobberi.stobberiException.StobberiException;
import stobberi.stobberiException.WrongDateTimeStobberiException;
import stobberi.components.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Represents a command that filters tasks in a {@link TaskList} based on a specific date.
 */
public class DateCommand extends Command {
    /**
     * The list of tasks to be filtered.
     */
    private TaskList taskList;

    /**
     * The date used for filtering tasks.
     */
    private String date;

    /**
     * Constructs a new {@code DateCommand} with the specified {@link TaskList} and date.
     *
     * @param taskList The list of tasks to be filtered.
     * @param date     The date used for filtering tasks, in the format "dd-MM-yyyy".
     */
    public DateCommand(TaskList taskList, String date) {
        this.taskList = taskList;
        this.date = date;
    }

    /**
     * Executes the command by filtering the {@link TaskList} based on the specified date.
     * If the date format is invalid, a {@link WrongDateTimeStobberiException} is thrown.
     *
     * @throws StobberiException if an error occurs during command execution or if the date format is invalid.
     */
    @Override
    public void execute() throws StobberiException {
        try {
            taskList.filterListByDate(date);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException("Date needs to be in the format dd-MM-yyyy\n Example: 27-12-2004\n" + e.getMessage());
        }
    }
}