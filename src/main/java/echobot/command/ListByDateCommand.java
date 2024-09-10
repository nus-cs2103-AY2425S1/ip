package echobot.command;

import echobot.exception.EchoBotException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.task.Task;

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
        assert !this.on.isBlank();
        final String PATTERN = "dd-MM-yyyy";

        try {
            LocalDate dateTime = LocalDate.parse(this.on, DateTimeFormatter.ofPattern(PATTERN));
            List<Task> tasksOccurringOn = taskList.getTasksOccurringOn(dateTime);
            return super.getTaskListResponse(tasksOccurringOn);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException(PATTERN);
        }
    }
}
