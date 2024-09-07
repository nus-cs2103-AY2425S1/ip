package muller.command;

import java.time.LocalDate;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;



/**
 * Command to list all tasks occurring on a specific date.
 */
public class OnCommand extends Command {
    private LocalDate date;

    /**
     * Constructs an OnCommand with the specified inputs.
     *
     * @param inputs The command inputs.
     * @throws MullerException If the input date format is invalid.
     */
    public OnCommand(String[] inputs) throws MullerException {
        if (inputs.length < 2) {
            throw new MullerException("Specify a date (e.g., 'on 2019-10-15')!");
        }
        try {
            this.date = LocalDate.parse(inputs[1].trim(), Task.INPUT_DATE_FORMATTER);
        } catch (Exception e) {
            throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskOnDate(tasks, date);
    }
}
