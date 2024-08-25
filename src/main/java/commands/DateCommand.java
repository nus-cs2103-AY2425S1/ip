package commands;

import java.time.LocalDateTime;

import exceptions.InvalidCommandException;
import tasks.TaskList;
import ui.Ui;
import utility.DateTimeUtility;

public class DateCommand extends Command {
    private final String input;

    public DateCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.input.isEmpty()) {
            throw new InvalidCommandException(
                    "No date provided. Please use the correct format: 'date [date_to_search]'"
            );
        }

        LocalDateTime date = DateTimeUtility.parse(this.input);
        if (date != null) {
            taskList.findByDate(date);
        }
    }
}
