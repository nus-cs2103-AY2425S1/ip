package moimoi.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;
import moimoi.exception.InvalidDateTimeException;
import moimoi.exception.InvalidIndexException;

/**
 * Represents a command to filter the task list, by a specific occurring date.
 */
public class FilterCommand extends Command {

    String dateString;

    /**
     * Constructs a command to filter the task list, by the specified occurring date.
     *
     * @param dateString Date to be checked against tasks' scheduled dates.
     */
    public FilterCommand(String dateString) {
        super(false);
        this.dateString = dateString;
    }

    /**
     * Executes task filtering, by occurring date.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @param ui MoiMoi's user interface.
     * @throws InvalidDateTimeException If the specified date is invalid.
     */
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
