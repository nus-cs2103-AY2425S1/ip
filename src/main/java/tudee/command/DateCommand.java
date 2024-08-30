package tudee.command;

import tudee.task.TaskList;
import tudee.task.Task;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.TudeeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    private final LocalDate date;

    public DateCommand(String dateStr) throws TudeeException {
        try {
            this.date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new TudeeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        boolean haveTask = false;
        for (Task task : tasks.get()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateTime().isEqual(date)) {
                    ui.showTask(deadline);
                    haveTask = true;
                }
            } else if (task instanceof Events) {
                Events events = (Events) task;
                if ((events.getStart().isBefore(date) && events.getEnd().isAfter(date))
                        || events.getStart().isEqual(date) || events.getEnd().isEqual(date)) {
                    ui.showTask(events);
                    haveTask = true;
                }
            }
        }
        if (!haveTask) {
            throw new TudeeException("You have no tasks on this date, " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
        }
    }
}