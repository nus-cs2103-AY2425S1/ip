package stobberi.command;

import java.time.format.DateTimeParseException;

import stobberi.components.TaskList;
import stobberi.stobberiexception.StobberiException;
import stobberi.stobberiexception.WrongDateTimeStobberiException;

/**
 * Represents a command that filters tasks in a {@link TaskList} by a specific date.
 * The date is provided as part of the command.
 */
public class DateCommand extends Command {
    /**
     * Constructs a DateCommand with the specified {@link TaskList} and the command string containing the date.
     *
     * @param taskList      The task list to be filtered.
     * @param restOfCommand The string containing the date to filter the tasks by.
     */
    public DateCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by filtering the {@link TaskList} based on the provided date.
     * If the date format is invalid (i.e., not in the format "dd-MM-yyyy"), a
     * {@link WrongDateTimeStobberiException} is thrown with a specific error message.
     *
     * @return A string output with the filtered tasks.
     * @throws StobberiException if an error occurs during command execution or if the date format is invalid.
     */
    @Override
    public String execute() throws StobberiException {
        String output;
        String date = getRestOfCommand();
        try {
            output = getTaskList().filterListByDate(date);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeStobberiException(
                    "Date needs to be in the format dd-MM-yyyy\n Example: 27-12-2004\n"
                            + e.getMessage());
        }
        return output;
    }
}
