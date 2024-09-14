package killua.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import killua.storage.Storage;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents a command to list all tasks that occur on a specific date.
 * This command parses the given date and displays the tasks scheduled for that date.
 */
public class OnCommand extends Command {
    private static final String INVALID_FORMAT_MESSAGE = "Please use the correct format for dates: yyyy-mm-dd";
    private final LocalDate date;

    /**
     * Constructs an OnCommand with the specified date string.
     * Parses the date string and initializes the date field.
     *
     * @param dateStr The date string in the format yyyy-mm-dd.
     * @throws KilluaException If the date string is not in the correct format.
     */
    public OnCommand(String dateStr) throws KilluaException {
        this.date = parseDate(dateStr);
    }

    /**
     * Parses the date string into a LocalDate object.
     * Throws a KilluaException if the date string is not in the correct format.
     *
     * @param dateStr The date string to be parsed.
     * @return The LocalDate object representing the parsed date.
     * @throws KilluaException If the date string is not in the correct format.
     */
    private LocalDate parseDate(String dateStr) throws KilluaException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new KilluaException(INVALID_FORMAT_MESSAGE);
        }
    }

    /**
     * Executes the command to display all tasks occurring on the specified date.
     * Shows the tasks scheduled for the date stored in this command using the user interface.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to display the tasks on the specified date.
     * @param storage The storage is not used in this command, but required for method signature.
     * @return A String representation of Killua's response.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasksOnDate(tasks, date);
    }
}
