package command;

import exception.EchoBotException;
import exception.InvalidDeadlineFormatException;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ListByDateCommand extends ListCommand {

    private final String on;

    public ListByDateCommand(String on) {
        this.on = on;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        String pattern = "dd-MM-yyyy";

        try {
            LocalDate dateTime = LocalDate.parse(this.on, DateTimeFormatter.ofPattern(pattern));
            List<Task> tasksOccurringOn = taskList.getTasksOccurringOn(dateTime);
            return super.getTaskListResponse(tasksOccurringOn);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException(pattern);
        }
    }
}
