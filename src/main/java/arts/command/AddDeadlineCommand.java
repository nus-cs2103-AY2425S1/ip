package arts.command;

import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;
import arts.task.Deadline;
import arts.ArtsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String details;
    private final DateTimeFormatter[] inputFormatters;

    public AddDeadlineCommand(TaskList tasks, Storage storage, Ui ui, String details, DateTimeFormatter[] inputFormatters) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.details = details;
        this.inputFormatters = inputFormatters;
    }

    @Override
    public void execute() throws ArtsException {
        String[] deadlineParts = details.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new ArtsException("The deadline must have a /by date.");
        }
        LocalDateTime deadlineDate = parseDate(deadlineParts[1]);
        tasks.addTask(new Deadline(deadlineParts[0], deadlineDate));
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n " + tasks.getTask(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    private LocalDateTime parseDate(String dateString) throws ArtsException {
        LocalDateTime date = null;
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                date = LocalDateTime.parse(dateString, formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        if (date == null) {
            throw new ArtsException("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.");
        }
        return date;
    }
}
