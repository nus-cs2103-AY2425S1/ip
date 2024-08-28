import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String details;
    private final DateTimeFormatter[] inputFormatters;

    public AddEventCommand(TaskList tasks, Storage storage, Ui ui, String details, DateTimeFormatter[] inputFormatters) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.details = details;
        this.inputFormatters = inputFormatters;
    }

    @Override
    public void execute() throws ArtsException {
        String[] eventParts = details.split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new ArtsException("The event must have /from and /to times.");
        }
        LocalDateTime eventFromDate = parseDate(eventParts[1]);
        LocalDateTime eventToDate = parseDate(eventParts[2]);
        tasks.addTask(new Event(eventParts[0], eventFromDate, eventToDate));
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
